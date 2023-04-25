package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final OrderMapper orderMapper;
    private final CartDbService cartDbService;

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.isStatus(),
                userDto.getUserKey(),
                cartDbService.getCartById(userDto.getUserId()),
                orderMapper.mapToOrderList(userDto.getOrders())
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.isStatus(),
                user.getUserKey(),
                user.getCart(),
                orderMapper.mapToOrderDtoList(user.getOrders())
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User>
                                                  userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }
}
