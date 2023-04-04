package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UserDto {
    private Long userId;
    private String username;
    private String status;
    private int userKey;
    private int cartId;
    private int ordersId;
}
