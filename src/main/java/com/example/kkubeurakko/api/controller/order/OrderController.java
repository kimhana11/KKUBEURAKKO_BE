package com.example.kkubeurakko.api.controller.order;

import com.example.kkubeurakko.api.controller.order.request.OrderStatusRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @MessageMapping("/start")
    @SendTo("/topic/orders")
    public void startOrder(OrderStatusRequest orderStatus) {
        // 주문 상태를 '접수'로 변경
       // return null;
    }
}
