package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import com.usermanagment.backend.mapper.UserMapper;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepo userRepo;
    private final UserMapper userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::userToUserDto).toList();
    }

    @Override
    public UserDto createUser(UserUpdateDto userUpdateDto) {
        User user = userMapper.userUpdateDtoToUser(userUpdateDto);

        user = userRepo.save(user);
        return userMapper.userToUserDto(user);
    }
}
