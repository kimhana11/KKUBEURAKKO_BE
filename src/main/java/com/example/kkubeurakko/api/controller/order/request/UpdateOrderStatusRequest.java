package com.example.kkubeurakko.api.controller.order.request;

import com.example.kkubeurakko.domain.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateOrderStatusRequest {
    private Long id;
    private String status;
    private int estimatedMinutes;
}
