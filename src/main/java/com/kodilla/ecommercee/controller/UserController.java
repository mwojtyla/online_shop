package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;

import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/shop/users")
@RequiredArgsConstructor
public class UserController {

    private final CartDbService cartDbService;
    private final UserDbService userDbService;
    private final UserMapper userMapper;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(userMapper.mapToUserDto(userDbService.getUser(userId)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userDbService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.save(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        cartDbService.save(user.getCart());
        userDbService.save(user);
        return ResponseEntity.ok().build();
    }
}
