package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductsInCartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartEntityTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductsInCartRepository productsInCartRepository;

    private Cart createCart(){
        Cart cart = new Cart();
        User user = new User();
        List<ProductsInCart> productsInCartList = new ArrayList<>();

        ProductsInCart productsInCart1 = new ProductsInCart();
        ProductsInCart productsInCart2 = new ProductsInCart();
        productsInCartList.add(productsInCart1);
        productsInCartList.add(productsInCart2);

        productsInCartRepository.save(productsInCart1);
        productsInCartRepository.save(productsInCart2);

        userRepository.save(user);

        cart.setUser(user);
        cart.setProductsInCart(productsInCartList);

        return cart;
    }

    @Test
    void testSaveCart(){
        //Given
        Cart cart = createCart();
        //When
        cartRepository.save(cart);
        Long cartId = cart.getCartId();
        //Then
        assertNotNull(cartId);
    }

    @Test
    void testFindCartById(){
        //Given
        Cart cart = createCart();
        cartRepository.save(cart);
        //When
        Optional<Cart> readCart = cartRepository.findById(cart.getCartId());
        Optional<User> readUser = userRepository.findById(cart.getUser().getUserId());
        Long readCartId = readCart.get().getCartId();
        Long readUserId = readUser.get().getUserId();
        //Then
        assertEquals(readCartId, cart.getCartId());
        assertEquals(readUserId, cart.getUser().getUserId());
    }
    @Test
    void deleteCartById(){
        //Given
        Cart cart = createCart();
        cartRepository.save(cart);
        Long cartId = cart.getCartId();
        Long userId = cart.getUser().getUserId();
        List<Long> productsInCartIdList = cart.getProductsInCart().stream()
                .map(ProductsInCart::getProductsInCartId)
                .collect(Collectors.toList());
        //When
        cartRepository.deleteById(cartId);
        userRepository.deleteById(userId);
        for (Long productsInCartId: productsInCartIdList) {
            productsInCartRepository.deleteById(productsInCartId);
        }
        //Then
        assertFalse(cartRepository.existsById(cartId));
        assertFalse(userRepository.existsById(userId));
        for (Long productsInCartId: productsInCartIdList) {
            assertFalse(productsInCartRepository.existsById(productsInCartId));
        }
    }
}
