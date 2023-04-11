package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;

    @ManyToOne
    @JoinColumn (name = "GROUP_ID")
    private Group group;
}
