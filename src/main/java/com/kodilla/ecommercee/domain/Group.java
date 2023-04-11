package com.kodilla.ecommercee.domain;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
}