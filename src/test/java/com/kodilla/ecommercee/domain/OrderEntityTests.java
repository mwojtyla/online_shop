package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.*;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Transactional
@SpringBootTest
public class OrderEntityTests {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    private Order createOrder1() {
        User user1 = new User();

        Order order1 = new Order();
        List<Order> orderList1 = new ArrayList<>();
        orderList1.add(order1);

        OrderItem orderItem1 = new OrderItem();
        List<OrderItem> orderItemList1 = new ArrayList<>();
        orderItemList1.add(orderItem1);

        orderItem1.setOrder(order1);
        orderItemRepository.save(orderItem1);

        user1.setOrders(orderList1);
        userRepository.save(user1);

        order1.setOrderStatus("In progress");
        order1.setDateOfOrder(LocalDate.of(2023, 2, 2));
        order1.setUser(user1);
        order1.setOrdersItems(orderItemList1);

        return order1;
    }

    private Order createOrder2() {
        User user2 = new User();

        Order order2 = new Order();
        List<Order> orderList2 = new ArrayList<>();
        orderList2.add(order2);

        OrderItem orderItem2 = new OrderItem();
        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem2);

        orderItem2.setOrder(order2);
        orderItemRepository.save(orderItem2);

        user2.setOrders(orderList2);
        userRepository.save(user2);

        order2.setOrderStatus("In progress");
        order2.setDateOfOrder(LocalDate.of(2023, 2, 2));
        order2.setUser(user2);
        order2.setOrdersItems(orderItemList2);

        return order2;
    }

    @Test
    void testSaveOrder() {
        // Given
        Order order = createOrder1();
        // When
        orderRepository.save(order);
        Long orderId = order.getOrderId();
        // Then
        assertNotEquals(0, orderId);
    }

    @Test
    void testFindAllOrder() {
        // Given
        Order order1 = createOrder1();
        Order order2 = createOrder2();
        orderRepository.save(order1);
        orderRepository.save(order2);
        // When
        List<Order> orderList = orderRepository.findAll();
        // Then
        assertEquals(2, orderList.size());
    }

    @Test
    void testFindOrderById() {
        // Given
        Order order = createOrder1();
        orderRepository.save(order);
        // When
        Optional<Order> orderReceived = orderRepository.findById(order.getOrderId());
        // Then
        assertTrue(orderReceived.isPresent());
        assertEquals("In progress", orderReceived.get().getOrderStatus());
    }

    @Test
    void testDeleteOrderById() {
        // Given
        Order order = createOrder1();
        orderRepository.save(order);
        Long orderId = order.getOrderId();
        OrderItem orderItem = order.getOrdersItems().get(0);
        // When
        orderItemRepository.deleteById(orderItem.getOrderItemId());
        orderRepository.deleteById(orderId);
        // Then
        assertEquals(Optional.empty(), orderRepository.findById(orderId));
        assertFalse(orderRepository.existsById(orderId));
    }
}
