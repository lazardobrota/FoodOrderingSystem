package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);
}
