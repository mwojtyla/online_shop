package com.kodilla.ecommercee;

import javax.persistence.*;

@Entity
@Table(name = "GENERIC_ENTITY")
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String val;

    public GenericEntity() {
    }

    public String getVal() {
        return val;
    }

    public Long getId() {

        return id;
    }

    public GenericEntity(String val) {

        this.val = val;
    }
}
