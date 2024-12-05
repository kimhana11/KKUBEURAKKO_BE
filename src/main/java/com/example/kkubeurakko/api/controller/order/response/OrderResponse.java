package com.example.kkubeurakko.api.controller.order.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private int totalAmount;
    private String orderStatus;
}
