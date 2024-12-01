package com.example.kkubeurakko.api.controller.order.request;

import com.example.kkubeurakko.domain.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class OrderStatusRequest {
    private Long id;
    private OrderStatus status;
}
