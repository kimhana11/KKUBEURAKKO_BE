package com.example.kkubeurakko.api.controller.order.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private boolean isMember;
    private String nickname;
    private String phone;
    private String type;
    private BigDecimal totalAmount;
    private String orderDate;
    private String storeRequests;
    private String deliveryInstructions;
    private String paymentMethod;
    private String orderStatus;
    private String roadName;
    private String detailedAddress;
    private String postalCode;
    private List<OrderItemDTO> orderItems;

    @Data
    public static class OrderItemDTO {
        private String name;
        private int quantity;
        private List<String> options; // 선택된 옵션의 라벨들
    }
}
