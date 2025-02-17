package com.example.kkubeurakko.api.service.order;

import com.example.kkubeurakko.api.controller.order.response.OrderResponse;
import com.example.kkubeurakko.api.exception.order.OrderNotFoundException;
import com.example.kkubeurakko.api.service.order.mapper.OrderMapper;
import com.example.kkubeurakko.domain.menuOption.OptionRepository;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderRepository;
import com.example.kkubeurakko.domain.order.OrderStatus;
import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public OrderResponse updateOrderStatus(Long id, String newStatus, Integer estimatedMinutes) {
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
            order.setEstimatedCompletionTime(LocalDateTime.now().plusMinutes(estimatedMinutes));
        } else if (status == OrderStatus.COMPLETED) {
            order.setEstimatedCompletionTime(order.getEstimatedCompletionTime());
        }
        order.setOrderStatus(status);
        orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(order, optionRepository);
    }


    public List<OrderResponse> getAllOrders() {
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

    //주문 옵션 추가금액을 계산하는 매서드
    private BigDecimal calculateTotalAdditionalPrice(Order order) {
        return order.getOrderItems().stream()
                .flatMap(orderItem -> orderItem.getSelectedOptionIds().stream())
                .distinct()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        optionIds -> optionIds.isEmpty() ? BigDecimal.ZERO : optionRepository.findTotalAdditionalPriceByOptionIds(optionIds)
                ));
    }
}
