package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        return userMapper.mapToUserDtoList(users) ;
    }

    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return new UserDto(1L, "mike", true, 555L, new Cart(), new ArrayList<>());
    }

    @DeleteMapping
    public void deleteUser(Long userId) {
    }

    @PutMapping
    public UserDto updateUser(UserDto userDto) {
        return new UserDto(1L, "mikeeee", true, 555L, new Cart(), new ArrayList<>());
    }

    @PostMapping
    public void createUser(UserDto userDto) {
    }
}
