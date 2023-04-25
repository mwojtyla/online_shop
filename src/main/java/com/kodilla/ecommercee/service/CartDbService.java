package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDbService {

    private final CartRepository cartRepository;

    @Autowired
    public CartDbService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

}
