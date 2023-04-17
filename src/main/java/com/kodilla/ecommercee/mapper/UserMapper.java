package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;

import java.util.List;

public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.isStatus(),
                userDto.getUserKey(),
                userDto.getCart(),
                userDto.getOrders()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.isStatus(),
                user.getUserKey(),
                user.getCart(),
                user.getOrders()
        );
    }

    public List<UserDto> mapToUserDtoList (final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
