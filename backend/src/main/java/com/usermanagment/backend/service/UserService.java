package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.mapper.UserMapper;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private IUserRepo userRepo;
    private UserMapper userMapper;


    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::userToUserDto).toList();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);

        user = userRepo.save(user);
        return userMapper.userToUserDto(user);
    }
}
