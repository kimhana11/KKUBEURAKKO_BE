package com.example.kkubeurakko.api.controller.order;

import com.example.kkubeurakko.api.controller.order.request.OrderStatusRequest;
import com.example.kkubeurakko.api.service.order.OrderService;
import com.example.kkubeurakko.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate; // 소켓 메시지 전송

    @MessageMapping("/start")
    public void startOrder(OrderStatusRequest orderStatusRequest, Principal principal) {
        // 현재 사용자의 userNumber를 Principal에서 가져오기
        String currentUserNumber = principal.getName();
        // 주문 상태를 변경
        Order updatedOrder = orderService.updateOrderStatus(orderStatusRequest);

        if (!updatedOrder.getUser().getUserNumber().equals(currentUserNumber)) {
            throw new AccessDeniedException("Unauthorized access");
        }
        // 특정 사용자만 해당 메시지를 수신하도록 경로 설정
        String userDestination = "/user/" + updatedOrder.getUser().getId() + "/queue/orders";
        messagingTemplate.convertAndSend(userDestination, updatedOrder);

    }
}
