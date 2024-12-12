package com.example.kkubeurakko.api.service.order;

import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderRepository;
import com.example.kkubeurakko.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order updateOrderStatus(Long id, String newStatus,Integer estimatedMinutes) {
        // 주문 ID로 주문 찾기
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

        OrderStatus status = OrderStatus.valueOf(newStatus); // 상태 문자열을 Enum으로 변환

        if (status == OrderStatus.RECEIVED && estimatedMinutes != null) {
            // 접수 상태로 변경 시, 예상 완료 시간 계산
            order.setEstimatedCompletionTime(LocalDateTime.now().plusMinutes(estimatedMinutes));
        } else if (status == OrderStatus.COMPLETED) {
            // 완료 상태로 변경 시, 예상 완료 시간은 유지됨
            order.setEstimatedCompletionTime(order.getEstimatedCompletionTime());
        }
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }


}