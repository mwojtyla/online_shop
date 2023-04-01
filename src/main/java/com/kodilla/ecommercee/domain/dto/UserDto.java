package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserDto {
    private int userId;
    private String username;
    private int status;
    private int userKey;
    private int cartId;
    private int ordersId;

    public UserDto(int userId, String username, int status, int userKey) {
        this.userId = userId;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
}
