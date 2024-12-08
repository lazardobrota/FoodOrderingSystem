package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserUpdateDto userUpdateDto);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    boolean deleteUser(Long id);
}
