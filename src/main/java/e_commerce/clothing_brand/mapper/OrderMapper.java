package e_commerce.clothing_brand.mapper;

import e_commerce.clothing_brand.dto.order.OrderItemDTO;
import e_commerce.clothing_brand.dto.order.OrderResponseDTO;
import e_commerce.clothing_brand.entity.order.Order;
import e_commerce.clothing_brand.entity.order.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .user(UserMapper.toSimpleDTO(order.getUser()))
                .items(mapItems(order.getItems()))
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    private static List<OrderItemDTO> mapItems(List<OrderItem> items) {
        return items.stream().map(i -> OrderItemDTO.builder()
                .id(i.getId())
                .productId(i.getProduct().getId())
                .productName(i.getProduct().getName())
                .quantity(i.getQuantity())
                .price(i.getPrice())
                .build()
        ).collect(Collectors.toList());
    }
}