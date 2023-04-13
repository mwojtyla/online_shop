package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/cart")
@RequiredArgsConstructor
public class CartController {

    @PostMapping
    public Long createEmptyCart(){
        Long cartId = 1L;
        return cartId;
    }
    @GetMapping("{cartId}")
    public List<ProductDto> fetchProductsInCart(@PathVariable Long cartId) {
        return new ArrayList<>();
    }
    @PutMapping("{cartId}/{productId}")
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId){
        System.out.println("product " + productId + " added to " + cartId);
    }
    @DeleteMapping("{cartId}/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId){
        System.out.println("element " + productId + " removed from " + cartId);
    }
    @DeleteMapping("{cartId}")
    public void removeEverythingFromCart(@PathVariable Long cartId){
        System.out.println("Everything removed from cart " + cartId);
    }
    @PostMapping("{cartId}/create_order")
    public void createOrder(@PathVariable Long cartId){
        System.out.println("Order created for cart " + cartId);
    }

}
