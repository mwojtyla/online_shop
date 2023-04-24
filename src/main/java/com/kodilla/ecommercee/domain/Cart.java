package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "CART_ID", unique = true)
    private Long cartId;

    @OneToOne(
            fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User userId;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cartId")
    private List<ProductsInCart> productsInCart;
}

