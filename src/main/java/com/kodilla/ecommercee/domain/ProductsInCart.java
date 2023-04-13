package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS_IN_CART")
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
    private Product product;

    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;

}
