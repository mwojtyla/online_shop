package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserDto {
    private Long userId;
    private String username;
    private String status;
    private int userKey;
    private int cartId;
    private int ordersId;

    public UserDto(Long userId, String username, String status, int userKey) {
        this.userId = userId;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
}
