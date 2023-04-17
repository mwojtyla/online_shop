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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Long testUserId = user.getUserId();
        assertNotEquals(0, testUserId);
        userRepository.delete(user);
    }

    @Test
    void testFindAllUsers() {
        //Given
        User user = prepareUserData();

        //When
        userRepository.save(user);
        List<User> userList = (List<User>) userRepository.findAll();
        int userQuantity = userList.size();

        //Then
        assertEquals(userQuantity, 1);
        userRepository.delete(user);
    }

    @Test
    void testFindUserById() {
        //Given
        User user = prepareUserData();

        //When
        userRepository.save(user);
        Long testUserId = user.getUserId();
        Optional<User> readUser = userRepository.findById(testUserId);

        //Then
        assertTrue(readUser.isPresent());
        userRepository.delete(user);
    }

    @Test
    void testDeleteById() {
        //Given
        User user = prepareUserData();

        //When
        userRepository.save(user);
        Long testUserId = user.getUserId();

        userRepository.deleteById(testUserId);
        List<User> userList = (List<User>) userRepository.findAll();

        //Then
        assertTrue(userList.isEmpty());
        assertFalse(userRepository.existsById(testUserId));
    }
}
