package com.example.kkubeurakko.api.controller.order.response;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        boolean member,
        String nickname,
        String phone,
        String type,
        BigDecimal totalAmount,
        String orderDate,
        String storeRequests,
        String deliveryInstructions,
        String paymentMethod,
        String orderStatus,
        String roadName,
        String detailedAddress,
        String postalCode,
        List<OrderItemDTO> orderItems
) {

    public static record OrderItemDTO(
            String name,
            int quantity,
            List<String> options // 선택된 옵션의 라벨들
    ) {}
}
