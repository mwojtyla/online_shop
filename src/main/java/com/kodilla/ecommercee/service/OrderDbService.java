package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.exception.OrderItemNotFoundException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }
    public void deleteOrder(final Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
    public OrderItem getOrderItem(final Long orderItemId) throws OrderItemNotFoundException {
        return orderItemRepository.findById(orderItemId).orElseThrow(OrderItemNotFoundException::new);
    }
    public OrderItem saveOrderItem(final OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    public void deleteOrderItem(final Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
