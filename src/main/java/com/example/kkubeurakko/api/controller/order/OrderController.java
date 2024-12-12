package com.example.kkubeurakko.api.controller.order;

import com.example.kkubeurakko.api.controller.order.request.UpdateOrderStatusRequest;
import com.example.kkubeurakko.api.service.order.OrderService;
import com.example.kkubeurakko.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate; // 소켓 메시지 전송


    @MessageMapping("/start")
    public ResponseEntity<Order> updateOrderStatus(@Payload UpdateOrderStatusRequest request) {
        Order updatedOrder = orderService.updateOrderStatus(request.getOrderId(), request.getStatus(), request.getEstimatedMinutes());
        messagingTemplate.convertAndSend("/topic/orders/" + request.getOrderId(), updatedOrder); // 상태 변경을 실시간으로 전송
        return ResponseEntity.ok().build();
    }
}
