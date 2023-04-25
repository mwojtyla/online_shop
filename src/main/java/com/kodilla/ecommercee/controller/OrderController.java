package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import com.kodilla.ecommercee.exception.OrderItemNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/shop/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping("/items")
    public ResponseEntity<List<OrderItemDto>> getItems() {
        List<OrderItem> ordersItems = orderDbService.getAllOrderItems();
        return ResponseEntity.ok(orderMapper.mapToOrderItemDtoList(ordersItems));
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        Order order = orderDbService.getOrder(orderId);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }

    @GetMapping("/items/{orderItemId}")
    public ResponseEntity<OrderItemDto> getOrderItem(@PathVariable Long orderItemId) throws OrderItemNotFoundException {
        OrderItem orderItem = orderDbService.getOrderItem(orderItemId);
        return ResponseEntity.ok(orderMapper.mapToOrderItemDto(orderItem));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/items")
    public ResponseEntity<Void> createOrderItem(@RequestBody OrderItemDto orderItemDto) throws OrderNotFoundException, ProductNotFoundException {
        OrderItem orderItem = orderMapper.mapToOrderItem(orderItemDto);
        orderDbService.saveOrderItem(orderItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderDbService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }

    @PutMapping("/items")
    public ResponseEntity<OrderItemDto> updateOrderItem(@RequestBody OrderItemDto orderItemDto) throws OrderNotFoundException, ProductNotFoundException {
        OrderItem orderItem = orderMapper.mapToOrderItem(orderItemDto);
        OrderItem savedOrderItem = orderDbService.saveOrderItem(orderItem);
        return ResponseEntity.ok(orderMapper.mapToOrderItemDto(savedOrderItem));
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderDbService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        orderDbService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok().build();
    }
}