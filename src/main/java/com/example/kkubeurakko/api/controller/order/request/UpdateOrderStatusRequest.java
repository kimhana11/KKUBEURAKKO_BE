package com.example.kkubeurakko.api.controller.order.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateOrderStatusRequest {
    private Long orderId;
    private String status;
    private int estimatedMinutes;
}
