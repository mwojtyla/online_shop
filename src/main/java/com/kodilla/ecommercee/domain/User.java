package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class User {
    private int userId;
    private String username;
    private int status;
    private int userKey;
    private int cartId;
    private int ordersId;

    public User(int userId, String username, int status, int userKey) {
        this.userId = userId;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
}
