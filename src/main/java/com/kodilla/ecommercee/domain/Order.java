package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long orderId;

    @NotNull
    @Column(name = "DATE_OF_ORDER")
    private LocalDate dateOfOrder;

    @NotNull
    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "order",
            fetch = FetchType.LAZY
    )
    private List<OrderItem> ordersItems = new ArrayList<>();
}
