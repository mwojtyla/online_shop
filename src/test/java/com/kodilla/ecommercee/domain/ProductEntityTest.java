package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductEntityTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductsInCartRepository productsInCartRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void shouldCreateProductEverywhere() {

        //Given
        Group group = new Group();
        group.setName("Test Group");
        groupRepository.save(group);
        Long savedGroupId = group.getGroupId();

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(11));
        product.setGroup(group);
        productRepository.save(product);
        Long savedProductId = product.getProductId();

        Cart cart = new Cart();
        cartRepository.save(cart);
        Long savedCartId = cart.getCartId();

        ProductsInCart cartItem1 = new ProductsInCart();
        cartItem1.setProductQuantity(2);
        cartItem1.setProduct(product);
        cartItem1.setCartId(cart);
        productsInCartRepository.save(cartItem1);
        Long savedCartItems1Id = cartItem1.getProductsInCartId();

        ProductsInCart cartItem2 = new ProductsInCart();
        cartItem2.setProductQuantity(3);
        cartItem2.setProduct(product);
        cartItem2.setCartId(cart);
        productsInCartRepository.save(cartItem2);
        Long savedCartItems2Id = cartItem2.getProductsInCartId();

        Order order = new Order();
        orderRepository.save(order);
        Long savedOrderId = order.getOrderId();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProductQuantity(4);
        orderItem1.setProduct(product);
        orderItemRepository.save(orderItem1);
        Long savedOrderItems1Id = orderItem1.getOrderItemId();

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProductQuantity(5);
        orderItem2.setProduct(product);
        orderItemRepository.save(orderItem2);
        Long savedOrderItems2Id = orderItem2.getOrderItemId();

        //Then
        assertTrue(productRepository.existsById(savedProductId));
        assertTrue(orderRepository.existsById(savedOrderId));
        assertTrue(cartRepository.existsById(savedCartId));
        assertTrue(productsInCartRepository.existsById(savedCartItems1Id));
        assertTrue(productsInCartRepository.existsById(savedCartItems2Id));
        assertTrue(orderItemRepository.existsById(savedOrderItems1Id));
        assertTrue(orderItemRepository.existsById(savedOrderItems2Id));
        assertTrue(groupRepository.existsById(savedGroupId));
    }

    @Test
    void shouldReadProductEverywhere() {
        // Given
        Group group = new Group();
        group.setName("Test Group");
        groupRepository.save(group);
        Long savedGroupId = group.getGroupId();

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.TEN);
        product.setGroup(group);
        productRepository.save(product);
        Long savedProductId = product.getProductId();

        Cart cart = new Cart();
        cartRepository.save(cart);
        Long savedCartId = cart.getCartId();

        ProductsInCart cartItem1 = new ProductsInCart();
        cartItem1.setProductQuantity(2);
        cartItem1.setProduct(product);
        cartItem1.setCartId(cart);
        productsInCartRepository.save(cartItem1);
        Long savedCartItems1Id = cartItem1.getProductsInCartId();

        ProductsInCart cartItem2 = new ProductsInCart();
        cartItem2.setProductQuantity(3);
        cartItem2.setProduct(product);
        cartItem2.setCartId(cart);
        productsInCartRepository.save(cartItem2);
        Long savedCartItems2Id = cartItem2.getProductsInCartId();

        Order order = new Order();
        orderRepository.save(order);
        Long savedOrderId = order.getOrderId();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProductQuantity(4);
        orderItem1.setProduct(product);
        orderItemRepository.save(orderItem1);
        Long savedOrderItems1Id = orderItem1.getOrderItemId();

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProductQuantity(5);
        orderItem2.setProduct(product);
        orderItemRepository.save(orderItem2);
        Long savedOrderItems2Id = orderItem2.getOrderItemId();

        // When
        Product readProduct = productRepository.findById(savedProductId).orElse(null);
        Group readGroup = groupRepository.findById(savedGroupId).orElse(null);
        Cart readCart = cartRepository.findById(savedCartId).orElse(null);
        ProductsInCart readCartItems1 = productsInCartRepository.findById(savedCartItems1Id).orElse(null);
        ProductsInCart readCartItems2 = productsInCartRepository.findById(savedCartItems2Id).orElse(null);
        Order readOrder = orderRepository.findById(savedOrderId).orElse(null);
        OrderItem readOrderItems1 = orderItemRepository.findById(savedOrderItems1Id).orElse(null);
        OrderItem readOrderItems2 = orderItemRepository.findById(savedOrderItems2Id).orElse(null);

        // Then
        assertNotNull(readProduct);
        assertEquals("Test Product", readProduct.getName());
        assertEquals(BigDecimal.TEN, readProduct.getPrice());
        assertEquals(group, readProduct.getGroup());

        assertNotNull(readGroup);
        assertEquals("Test Group", readGroup.getName());
        assertEquals(group.getGroupId(), readGroup.getGroupId());

        assertNotNull(readCart);
        assertEquals(cart.getCartId(), readCart.getCartId());

        assertNotNull(readCartItems1);
        assertEquals(product, readCartItems1.getProduct());
        assertEquals(cart, readCartItems1.getCartId());
        assertEquals(2, readCartItems1.getProductQuantity());

        assertNotNull(readCartItems2);
        assertEquals(product, readCartItems2.getProduct());
        assertEquals(cart, readCartItems2.getCartId());
        assertEquals(3, readCartItems2.getProductQuantity());

        assertNotNull(readOrder);
        assertEquals(order.getOrderId(), readOrder.getOrderId());

        assertNotNull(readOrderItems1);
        assertEquals(product, readOrderItems1.getProduct());
        assertEquals(4,  readOrderItems1.getProductQuantity());

        assertNotNull(readOrderItems2);
        assertEquals(product, readOrderItems2.getProduct());
        assertEquals(5,  readOrderItems2.getProductQuantity());
    }

    @Test
    void shouldDeleteProduct() {
        // Given
        Group group = new Group();
        group.setName("Test Group");
        groupRepository.save(group);
        Long savedGroupId = group.getGroupId();

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(11));
        product.setGroup(group);
        productRepository.save(product);
        Long savedProductId = product.getProductId();

        Cart cart = new Cart();
        cartRepository.save(cart);
        Long savedCartId = cart.getCartId();

        ProductsInCart cartItem1 = new ProductsInCart();
        cartItem1.setProductQuantity(2);
        cartItem1.setProduct(product);
        cartItem1.setCartId(cart);
        productsInCartRepository.save(cartItem1);
        Long savedCartItems1Id = cartItem1.getProductsInCartId();

        ProductsInCart cartItem2 = new ProductsInCart();
        cartItem2.setProductQuantity(3);
        cartItem2.setProduct(product);
        cartItem2.setCartId(cart);
        productsInCartRepository.save(cartItem2);
        Long savedCartItems2Id = cartItem2.getProductsInCartId();

        Order order = new Order();
        orderRepository.save(order);
        Long savedOrderId = order.getOrderId();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProductQuantity(4);
        orderItem1.setProduct(product);
        orderItemRepository.save(orderItem1);
        Long savedOrderItems1Id = orderItem1.getOrderItemId();

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProductQuantity(5);
        orderItem2.setProduct(product);
        orderItemRepository.save(orderItem2);
        Long savedOrderItems2Id = orderItem2.getOrderItemId();

        // When
        productRepository.deleteById(savedProductId);
        groupRepository.deleteById(savedGroupId);
        cartRepository.deleteById(savedCartId);
        productsInCartRepository.deleteById(savedCartItems1Id);
        productsInCartRepository.deleteById(savedCartItems2Id);
        orderRepository.deleteById(savedOrderId);
        orderItemRepository.deleteById(savedOrderItems1Id);
        orderItemRepository.deleteById(savedOrderItems2Id);

        // Then
        assertFalse(productRepository.existsById(savedProductId));
        assertFalse(groupRepository.existsById(savedGroupId));
        assertFalse(cartRepository.existsById(savedCartId));
        assertFalse(productsInCartRepository.existsById(savedCartItems1Id));
        assertFalse(productsInCartRepository.existsById(savedCartItems2Id));
        assertFalse(orderRepository.existsById(savedOrderId));
        assertFalse(orderItemRepository.existsById(savedOrderItems1Id));
        assertFalse(orderItemRepository.existsById(savedOrderItems2Id));

    }
}
