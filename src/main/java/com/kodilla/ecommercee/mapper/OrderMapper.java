package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import com.kodilla.ecommercee.domain.dto.OrderItemDtoWithoutOrderId;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private final ProductDbService productDbService;
    private final OrderDbService orderDbService;
    private final UserDbService userDbService;

    public OrderMapper(ProductDbService productDbService, OrderDbService orderDbService, UserDbService userDbService) {
        this.productDbService = productDbService;
        this.orderDbService = orderDbService;
        this.userDbService = userDbService;
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getDateOfOrder(),
                orderDto.getOrderStatus(),
                userDbService.getUser(orderDto.getUserId()),
                orderDto.getOrdersItemsDtosWithoutOrderId().stream()
                        .map(s -> {
                            try {
                                return new OrderItem(
                                        s.getOrderItemId(),
                                        s.getPrice(),
                                        productDbService.getProductById(s.getProductsId()),
                                        orderDbService.getOrder(orderDto.getOrderId()),
                                        s.getProductQuantity()
                                );
                            } catch (ProductNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (OrderNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList())
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getDateOfOrder(),
                order.getOrderStatus(),
                order.getUser().getUserId(),
                mapToOrderItemDtoListWithoutOrderId(order.getOrdersItems())
        );
    }

    public List<OrderItemDtoWithoutOrderId> mapToOrderItemDtoListWithoutOrderId(final List<OrderItem> ordersItems) {
        return ordersItems.stream()
                .map(s -> new OrderItemDtoWithoutOrderId(
                        s.getOrderItemId(),
                        s.getPrice(),
                        s.getProduct().getProductId(),
                        s.getProductQuantity()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderItemDto> mapToOrderItemDtoList(final List<OrderItem> ordersItems) {
        return ordersItems.stream()
                .map(this::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public List<OrderItem> mapToOrderItemList(List<OrderItemDto> orderItemDtosList) {
        return orderItemDtosList.stream()
                .map(orderItemDto -> {
                    try {
                        return mapToOrderItem(orderItemDto);
                    } catch (ProductNotFoundException | OrderNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public OrderItemDto mapToOrderItemDto(final OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getOrderItemId(),
                orderItem.getPrice(),
                orderItem.getProduct().getProductId(),
                orderItem.getOrder().getOrderId(),
                orderItem.getProductQuantity()
        );
    }

    public OrderItem mapToOrderItem(final OrderItemDto orderItemDto) throws ProductNotFoundException, OrderNotFoundException {
        return new OrderItem(
                orderItemDto.getOrderItemId(),
                orderItemDto.getPrice(),
                productDbService.getProductById(orderItemDto.getProductsId()),
                orderDbService.getOrder(orderItemDto.getOrderId()),
                orderItemDto.getProductQuantity()
        );
    }

    public List<Order> mapToOrderList(final List<OrderDto> ordersDtos) {
        List<Order> ordersList = new ArrayList<>();
        for (OrderDto orderDto : ordersDtos) {
            Order order;
            try {
                order = mapToOrder(orderDto);
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
            ordersList.add(order);
        }
        return ordersList;
    }
}