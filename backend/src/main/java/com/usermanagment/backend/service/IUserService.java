package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.user.UserDto;
import com.usermanagment.backend.dto.user.UserLoginDto;
import com.usermanagment.backend.dto.user.UserTokenDto;
import com.usermanagment.backend.dto.user.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserById(Long id);

    UserDto createUser(UserUpdateDto userUpdateDto);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    boolean deleteUser(Long id);

    UserTokenDto login(UserLoginDto userLoginDto);

    UserUpdateDto getEditableUserById(Long id);
}
