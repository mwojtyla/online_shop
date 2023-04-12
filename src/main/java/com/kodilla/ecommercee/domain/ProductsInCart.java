package com.kodilla.ecommercee.domain;

import javax.persistence.*;

@Entity(name = "PRODUCTS_IN_CART")
public class ProductsInCart {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCTS_IN_CART_ID", unique = true)
    private Long productsInCartId;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cartId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product productId;

    @Column(name = "QUANTITY")
    private int productQuantity;
}
