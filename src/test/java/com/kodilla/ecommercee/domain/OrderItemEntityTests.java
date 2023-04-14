package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class OrderItemEntityTests {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    GroupRepository groupRepository;

    private OrderItem createOrderItem1() {
        Order order1 = new Order();
        Product product1 = new Product();
        Group group1 = new Group();

        OrderItem orderItem1 = new OrderItem();
        List<OrderItem> orderItemList1 = new ArrayList<OrderItem>();
        orderItemList1.add(orderItem1);

        groupRepository.save(group1);

        order1.setOrdersItems(orderItemList1);
        orderRepository.save(order1);

        product1.setOrderItem(orderItemList1);
        product1.setGroup(group1);
        productRepository.save(product1);

        orderItem1.setOrder(order1);
        orderItem1.setPrice(new BigDecimal(120));
        orderItem1.setProduct(product1);
        orderItem1.setProductQuantity(5);

        return orderItem1;
    }

    private OrderItem createOrderItem2() {
        Order order2 = new Order();
        Product product2 = new Product();
        Group group2 = new Group();

        OrderItem orderItem2 = new OrderItem();
        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem2);

        groupRepository.save(group2);

        order2.setOrdersItems(orderItemList2);
        orderRepository.save(order2);

        product2.setOrderItem(orderItemList2);
        product2.setGroup(group2);
        productRepository.save(product2);

        orderItem2.setOrder(order2);
        orderItem2.setPrice(new BigDecimal(90));
        orderItem2.setProduct(product2);
        orderItem2.setProductQuantity(10);

        return orderItem2;
    }

    @Test
    void testSaveOrderItem() {
        // Given
        OrderItem orderItem = createOrderItem1();
        // When
        orderItemRepository.save(orderItem);
        Long orderItemId = orderItem.getOrderItemId();
        // Then
        assertNotEquals(0, orderItemId);
    }

    @Test
    void testFindAllOrderItem() {
        // Given
        OrderItem orderItem1 = createOrderItem1();
        OrderItem orderItem2 = createOrderItem2();
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        // When
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        // Then
        assertEquals(2, orderItemList.size());
    }

    @Test
    void testFindOrderItemById() {
        // Given
        OrderItem orderItem = createOrderItem1();
        orderItemRepository.save(orderItem);
        // When
        Optional<OrderItem> orderItemReceived = orderItemRepository.findById(orderItem.getOrderItemId());
        // Then
        assertTrue(orderItemReceived.isPresent());
        assertEquals(5, orderItemReceived.get().getProductQuantity());
    }

    @Test
    void testDeleteOrderItemById() {
        // Given
        OrderItem orderItem = createOrderItem1();
        orderItemRepository.save(orderItem);
        Long orderItemId = orderItem.getOrderItemId();
        // When
        orderItemRepository.deleteById(orderItemId);
        // Then
        assertEquals(Optional.empty(), orderItemRepository.findById(orderItemId));
    }

}
