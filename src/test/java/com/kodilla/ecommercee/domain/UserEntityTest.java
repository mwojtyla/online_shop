package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserEntityTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    private User prepareUserData() {
        Order order1 = new Order();
        Order order2 = new Order();
        Cart cart = new Cart();
        User user = new User();

        cartRepository.save(cart);
        orderRepository.save(order1);
        orderRepository.save(order2);

        order1.setDateOfOrder(LocalDate.now());
        order1.setOrderStatus("done");
        order1.setUser(user);
        order1.setOrdersItems(new ArrayList<>());

        order2.setDateOfOrder(LocalDate.now());
        order1.setOrderStatus("done");
        order2.setUser(user);
        order2.setOrdersItems(new ArrayList<>());

        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order1);
        ordersList.add(order2);

        user.setUsername("Mike");
        user.setStatus(true);
        user.setUserKey(123L);
        user.setCart(cart);
        user.setOrders(ordersList);

        cart.setUser(user);
        cart.setProductsInCart(new ArrayList<>());
        return user;
    }

    @Test
    void testSaveUser() {
        //Given
        User user = prepareUserData();

        //When
        userRepository.save(user);

        //Then
        assertNotNull(user.getUserId());
        assertNotNull(user.getCart().getCartId());
        assertNotNull(user.getOrders());
    }


    @Test
    void testFindUserById() {
        //Given
        User user = prepareUserData();

        //When
        userRepository.save(user);

        Optional<User> readUser = userRepository.findById(user.getUserId());
        Optional<Cart> readCart = cartRepository.findById(user.getCart().getCartId());

        Long testUserId = readUser.get().getUserId();

        //Then
        assertTrue(readUser.isPresent());
        assertTrue(readCart.isPresent());
        assertEquals(testUserId, user.getUserId());
    }

    @Test
    void testDeleteById() {
        //Given
        User user = prepareUserData();

        //When
        userRepository.save(user);

        userRepository.deleteById(user.getUserId());
        cartRepository.deleteById(user.getCart().getCartId());

        //Then
        assertFalse(userRepository.existsById(user.getUserId()));
        assertFalse(cartRepository.existsById(user.getCart().getCartId()));
    }
}

