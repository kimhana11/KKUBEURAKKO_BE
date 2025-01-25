package com.example.kkubeurakko.api.service.order;

import com.example.kkubeurakko.api.controller.order.response.OrderResponseDTO;
import com.example.kkubeurakko.api.exception.order.OrderNotFoundException;
import com.example.kkubeurakko.api.service.order.mapper.OrderMapper;
import com.example.kkubeurakko.domain.menuOption.OptionRepository;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderItem;
import com.example.kkubeurakko.domain.order.OrderRepository;
import com.example.kkubeurakko.domain.order.OrderStatus;
import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OptionRepository optionRepository;

    @Transactional
    public OrderResponseDTO updateOrderStatus(Long id, String newStatus, Integer estimatedMinutes) {
        // 주문 ID로 주문 찾기
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(BadResponseMsgEnum.ORDER_NOT_FOUND));

        OrderStatus status;
        try {
            status = OrderStatus.valueOf(newStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 주문 상태입니다. 상태: " + newStatus, e);
        }

        if (status == OrderStatus.RECEIVED && estimatedMinutes != null) {
            // 접수 상태로 변경 시, 예상 완료 시간 계산
            order.setEstimatedCompletionTime(LocalDateTime.now().plusMinutes(estimatedMinutes));
        } else if (status == OrderStatus.COMPLETED) {
            // 완료 상태로 변경 시, 예상 완료 시간은 유지됨
            order.setEstimatedCompletionTime(order.getEstimatedCompletionTime());
        }
        order.setOrderStatus(status);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order, optionRepository);
    }


    //@Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        // 모든 주문을 조회하고 변환
        List<Order> orders = orderRepository.findAll();

        // 주문이 없을 경우 예외 처리
        if (orders.isEmpty()) {
            throw new OrderNotFoundException(BadResponseMsgEnum.ORDERS_NOT_FOUND);
        }

        // 주문을 OrderResponseDTO로 변환하여 반환
        return orders.stream()
                .map(order -> orderMapper.toOrderResponseDTO(order, optionRepository))
                .collect(Collectors.toList());
    }
}
