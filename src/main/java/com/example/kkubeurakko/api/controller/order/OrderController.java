package com.example.kkubeurakko.api.controller.order;

import com.example.kkubeurakko.api.controller.order.request.OrderStatusRequest;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {


    @MessageMapping("/start")
    public void startOrder(OrderStatusRequest orderStatusRequest) {
        // 주문 상태를 'RECEIVED'로 변경
        Order updatedOrder = orderService.updateOrderStatus(orderStatusRequest.getOrderId(), OrderStatus.RECEIVED);

        // 변경된 주문 정보를 소켓 구독자들에게 전달
        messagingTemplate.convertAndSend("/topic/orders", updatedOrder);
    }
}
