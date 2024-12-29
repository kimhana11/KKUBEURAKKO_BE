package com.example.kkubeurakko.api.controller.order;

import com.example.kkubeurakko.api.controller.order.request.UpdateOrderStatusRequest;
import com.example.kkubeurakko.api.controller.order.response.OrderResponseDTO;
import com.example.kkubeurakko.api.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate; // 소켓 메시지 전송


    @MessageMapping("/start")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@Payload UpdateOrderStatusRequest request) {
        OrderResponseDTO updatedOrder =
                orderService.updateOrderStatus(request.getOrderId(), request.getStatus(), request.getEstimatedMinutes());
        messagingTemplate.convertAndSend("/topic/orders/" + request.getOrderId(), updatedOrder); // 상태 변경을 실시간으로 전송
        return ResponseEntity.ok().build();
    }


    // 여러 주문 반환
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
