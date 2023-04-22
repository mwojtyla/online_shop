package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "PRODUCT_GROUP")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID", unique = true)
    private Long groupId;


    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = Product.class,
            mappedBy = "group",
            fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
