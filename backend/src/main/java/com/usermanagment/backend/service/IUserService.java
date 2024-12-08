package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserUpdateDto userUpdateDto);
}
