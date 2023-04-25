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
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long userId;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "STATUS")
    private boolean status;

    @NotNull
    @Column(name = "USER_KEY")
    private Long userKey;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Order> orders;
}