package com.example.kkubeurakko.api.service.order;

import com.example.kkubeurakko.api.controller.order.request.OrderStatusRequest;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderRepository;
import com.example.kkubeurakko.domain.order.OrderStatus;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order updateOrderStatus(Long id, OrderStatus status) {
        // 주문 ID로 주문 찾기
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 ID: " + id));

        // 상태 업데이트
        order.setOrderStatus(status);

        // 저장
        return orderRepository.save(order);
    }
}