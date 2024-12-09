package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserLoginDto;
import com.usermanagment.backend.dto.UserTokenDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserById(Long id);

    UserDto createUser(UserUpdateDto userUpdateDto);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    boolean deleteUser(Long id);

    UserTokenDto login(UserLoginDto userLoginDto);

    UserUpdateDto getUserByIdEdit(Long id);
}
