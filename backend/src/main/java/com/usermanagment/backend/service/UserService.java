package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import com.usermanagment.backend.mapper.UserMapper;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findByUserId(id).orElse(null);

        assert user != null;
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserUpdateDto userUpdateDto) {
        User user = userMapper.userUpdateDtoToUser(userUpdateDto);

        user = userRepo.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepo.findByUserId(id).orElse(null);

        assert user != null;
        user = userMapper.updateUser(user, userUpdateDto);
        userRepo.save(user);

        return userMapper.userToUserDto(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepo.deleteById(id);
        return true;
    }
}
