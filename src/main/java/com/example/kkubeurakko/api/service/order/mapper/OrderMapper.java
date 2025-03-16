package com.example.kkubeurakko.api.service.order.mapper;

import com.example.kkubeurakko.api.controller.order.response.OrderResponse;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.address.repository.AddressRepository;
import com.example.kkubeurakko.domain.menuOption.repository.OptionRepository;
import com.example.kkubeurakko.domain.order.Order;
import com.example.kkubeurakko.domain.order.OrderItem;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;

@Mapper(componentModel = "spring", uses = {OptionRepository.class, AddressRepository.class})
public interface OrderMapper {

    @Mapping(target = "member", expression = "java(order.getUser() != null)")
    @Mapping(target = "nickname", expression = "java(order.getUser() != null ? order.getUser().getNickname() : null)")
    @Mapping(target = "phone", expression = "java(order.getUser() != null ? order.getUser().getPhone() : order.getGuestOrder().getGuestContact())")
    @Mapping(target = "type", source = "orderType")
    @Mapping(target = "orderDate", expression = "java(order.getOrderDate().toLocalTime().toString())")
    @Mapping(target = "estimatedCompletionTime", expression = "java(order.getEstimatedCompletionTime().toLocalTime().toString())")
    @Mapping(target = "paymentMethod", expression = "java(order.getPaymentMethod().getText())")
    @Mapping(target = "orderStatus", expression = "java(order.getOrderStatus().getText())")

    // 주소 정보 매핑
    @Mapping(target = "roadName", source = "order", qualifiedByName = "mapRoadName")
    @Mapping(target = "detailedAddress", source = "order", qualifiedByName = "mapDetailedAddress")
    @Mapping(target = "postalCode", source = "order", qualifiedByName = "mapPostalCode")

    @Mapping(target = "orderItems", source = "orderItems")
    OrderResponse toOrderResponseDTO(Order order, @Context OptionRepository optionRepository, @Context AddressRepository addressRepository);

    @Mapping(target = "name", source = "menu.name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "options", source = "selectedOptionIds", qualifiedByName = "mapOptions")
    OrderResponse.OrderItemDTO toOrderItemDTO(OrderItem orderItem, @Context OptionRepository optionRepository);

    @Named("mapOptions")
    default List<String> mapOptions(List<Long> optionIds, @Context OptionRepository optionRepository) {
        if (optionIds == null || optionIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> labels = optionRepository.findLabelsByOptionIds(optionIds);
        System.out.println("Mapped options: " + labels);
        return labels;
    }


    @Named("mapRoadName")
    default String mapRoadName(Order order, @Context AddressRepository addressRepository) {
        if (order.getGuestOrder() != null) {
            return order.getGuestOrder().getRoadName(); // 비회원 주문
        }
        return addressRepository.findPrimaryAddressByUser(order.getUser())
                .map(Address::getRoadName)
                .orElse(null);
    }

    @Named("mapDetailedAddress")
    default String mapDetailedAddress(Order order, @Context AddressRepository addressRepository) {
        if (order.getGuestOrder() != null) {
            return order.getGuestOrder().getDetailedAddress();
        }
        return addressRepository.findPrimaryAddressByUser(order.getUser())
                .map(Address::getDetailedAddress)
                .orElse(null);
    }

    @Named("mapPostalCode")
    default String mapPostalCode(Order order, @Context AddressRepository addressRepository) {
        if (order.getGuestOrder() != null) {
            return order.getGuestOrder().getPostalCode();
        }
        return addressRepository.findPrimaryAddressByUser(order.getUser())
                .map(Address::getPostalCode)
                .orElse(null);
    }
}