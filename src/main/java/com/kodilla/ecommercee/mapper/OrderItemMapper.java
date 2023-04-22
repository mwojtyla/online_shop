package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemMapper {

    public OrderItem mapToOrderItem(final OrderItemDto orderItemDto) {
        return new OrderItem(
                orderItemDto.getOrderItemId(),
                orderItemDto.getPrice(),
                null,
                null,
                orderItemDto.getProductQuantity()
        );
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getOrderItemId(),
                null,
                orderItem.getPrice(),
                orderItem.getProduct().getProductId(),
                orderItem.getOrder().getOrderId(),
                orderItem.getProductQuantity()
        );
    }

    public List<OrderItem> mapToOrderItemList(final List<OrderItemDto> orderItemDtoList) {
        return orderItemDtoList.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> mapToOrderItemDtoList(final List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(this::mapToOrderItemDto)
                .collect(Collectors.toList());
    }
}
