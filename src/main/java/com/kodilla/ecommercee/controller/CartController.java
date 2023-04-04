package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public List<ProductDto> fetchProductsInCart(@PathVariable Long cartId){
        List<ProductDto> productList = new ArrayList<>();
        productList.add(new ProductDto(1L,
                "product 1",
                "Test",
                BigDecimal.valueOf(10),
                1L,
                1L,
                1L));
        productList.add(new ProductDto(2L,
                "product 2",
                "Test",
                BigDecimal.valueOf(20),
                2L,
                1L,
                1L));
        return productList;
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
