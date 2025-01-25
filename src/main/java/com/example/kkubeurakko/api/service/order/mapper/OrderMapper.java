package com.example.kkubeurakko.api.service.order.mapper;

import com.example.kkubeurakko.api.controller.order.response.OrderResponseDTO;
import com.example.kkubeurakko.domain.menuOption.OptionRepository;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderItem;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OptionRepository.class})
public interface OrderMapper {

    @Mapping(target = "member", expression = "java(order.getUser() != null)")
    @Mapping(target = "nickname", expression = "java(order.getUser() != null ? order.getUser().getNickname() : null)")
    @Mapping(target = "phone", expression = "java(order.getUser() != null ? order.getUser().getPhone() : order.getGuestOrder().getGuestContact())")
    @Mapping(target = "type", source = "orderType")
    @Mapping(target = "orderDate", expression = "java(order.getOrderDate().toLocalTime().toString())")
    @Mapping(target = "estimatedCompletionTime", expression = "java(order.getEstimatedCompletionTime().toLocalTime().toString())")
    @Mapping(target = "paymentMethod", expression = "java(order.getPaymentMethod().getText())")
    @Mapping(target = "orderStatus", expression = "java(order.getOrderStatus().getText())")
    @Mapping(target = "roadName", expression = "java(order.getGuestOrder() != null ? order.getGuestOrder().getRoadName() : null)")
    @Mapping(target = "detailedAddress", expression = "java(order.getGuestOrder() != null ? order.getGuestOrder().getDetailedAddress() : null)")
    @Mapping(target = "postalCode", expression = "java(order.getGuestOrder() != null ? order.getGuestOrder().getPostalCode() : null)")

    @Mapping(target = "orderItems", source = "orderItems")
    OrderResponseDTO toOrderResponseDTO(Order order, @Context OptionRepository optionRepository);

    @Mapping(target = "name", source = "menu.name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "options", source = "selectedOptionIds", qualifiedByName = "mapOptions")
    OrderResponseDTO.OrderItemDTO toOrderItemDTO(OrderItem orderItem, @Context OptionRepository optionRepository);

    @Named("mapOptions")
    default List<String> mapOptions(List<Long> optionIds, @Context OptionRepository optionRepository) {
        if (optionIds == null || optionIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> labels = optionRepository.findLabelsByOptionIds(optionIds);
        System.out.println("Mapped options: " + labels);
        return labels;
    }
}
