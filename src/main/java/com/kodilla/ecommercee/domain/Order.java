package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private User orderId;

}