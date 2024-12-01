package com.example.kkubeurakko.api.controller.order.request;

import com.example.kkubeurakko.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusRequest {
    private Long id;
    private OrderStatus status;
}
