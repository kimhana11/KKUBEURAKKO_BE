package com.example.kkubeurakko.api.service.order.mapper;

import com.example.kkubeurakko.api.controller.order.response.OrderResponseDTO;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface OrderMapper {

    @Mapping(target = "isMember", expression = "java(order.getUser() != null)")
    @Mapping(target = "nickname", expression = "java(order.getUser() != null ? order.getUser().getNickname() : null)")
    @Mapping(target = "phone", expression = "java(order.getUser() != null ? order.getUser().getPhone() : order.getGuestOrder().getGuestContact())")
    @Mapping(target = "type", source = "orderType")
    @Mapping(target = "orderDate", expression = "java(order.getOrderDate().toLocalTime().toString())")
    @Mapping(target = "paymentMethod", source = "paymentMethod.name")
    @Mapping(target = "orderStatus", source = "orderStatus.name")
    @Mapping(target = "roadName", expression = "java(order.getGuestOrder() != null ? order.getGuestOrder().getRoadName() : null)")
    @Mapping(target = "detailedAddress", expression = "java(order.getGuestOrder() != null ? order.getGuestOrder().getDetailedAddress() : null)")
    @Mapping(target = "postalCode", expression = "java(order.getGuestOrder() != null ? order.getGuestOrder().getPostalCode() : null)")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderResponseDTO toOrderResponseDTO(Order order);

    @Mapping(target = "name", source = "menu.name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "options", source = "selectedOptionIds", qualifiedByName = "mapOptions")
    OrderResponseDTO.OrderItemDTO toOrderItemDTO(OrderItem orderItem);

    @Named("mapOptions")
    default List<String> mapOptions(List<Long> optionIds) {
        // 옵션 ID를 라벨로 매핑하는 로직 필요 (옵션을 DB에서 조회하거나 별도 매퍼 필요)
        return optionIds.stream().map(id -> "옵션 " + id).collect(Collectors.toList());
    }
}
