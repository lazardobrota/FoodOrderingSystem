package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserLoginDto;
import com.usermanagment.backend.dto.UserTokenDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import com.usermanagment.backend.exception.UserException;
import com.usermanagment.backend.global.Global;
import com.usermanagment.backend.mapper.UserMapper;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IUserRepo;
import com.usermanagment.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepo userRepo;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public UserDto getUserById(Long id) throws UserException {
        User user = userRepo.findByUserId(id).orElseThrow(() -> new UserException("User not found with given id", HttpStatus.BAD_REQUEST));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserUpdateDto userUpdateDto) {
        userUpdateDto.setPassword(Global.hashPassword(userUpdateDto.getPassword()));
        User user = userMapper.userUpdateDtoToUser(userUpdateDto);

        user = userRepo.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) throws UserException {
        User user = userRepo.findByUserId(id).orElseThrow(() -> new UserException("User not found with given id", HttpStatus.BAD_REQUEST));

        if (!userUpdateDto.getPassword().equals(user.getPassword()))
            userUpdateDto.setPassword(Global.hashPassword(userUpdateDto.getPassword()));

        user = userMapper.updateUser(user, userUpdateDto);
        userRepo.save(user);

        return userMapper.userToUserDto(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public UserTokenDto login(UserLoginDto userLoginDto) throws UserException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(),
                userLoginDto.getPassword()
        ));

        User user = userRepo.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new UserException("User not found with given email", HttpStatus.BAD_REQUEST));

        return new UserTokenDto(userMapper.userToUserDto(user), jwtService.generateToken(user));
    }

    @Override
    public UserUpdateDto getUserByIdEdit(Long id) {
        User user = userRepo.findByUserId(id).orElseThrow(() -> new UserException("User not found with given id", HttpStatus.BAD_REQUEST));;
        return userMapper.userToUserUpdateDto(user);
    }
}
