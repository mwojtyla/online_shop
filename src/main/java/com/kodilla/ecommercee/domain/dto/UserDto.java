package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class UserDto {
    private Long userId;
    private String username;
    private boolean status;
    private Long userKey;
    private Cart cart;
    private List<Order> orders;
}
