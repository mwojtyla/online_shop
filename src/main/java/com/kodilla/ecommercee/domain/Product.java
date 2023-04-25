package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION", length = 1024)
    private String description;

    @NotNull
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group = new Group();

    @OneToMany(
            targetEntity =  ProductsInCart.class,
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    private List<ProductsInCart> productsInCarts = new ArrayList<>();

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItem = new ArrayList<>();

}