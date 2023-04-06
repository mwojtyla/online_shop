package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ORDER_ITEM_ID", unique = true)
    private Long orderItemId;

    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne(
            targetEntity = Product.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "PRODUCTS_ID")
    private Product product;

    @ManyToOne(
            targetEntity = Order.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
