package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.user.UserDto;
import com.usermanagment.backend.dto.user.UserLoginDto;
import com.usermanagment.backend.dto.user.UserTokenDto;
import com.usermanagment.backend.dto.user.UserUpdateDto;
import com.usermanagment.backend.exception.FoodException;
import com.usermanagment.backend.mapper.UserMapper;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.repository.IUserRepo;
import com.usermanagment.backend.security.JwtService;
import com.usermanagment.backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepo userRepo;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public UserDto getUserById(Long id) throws FoodException {
        User user = userRepo.findByUserId(id).orElseThrow(() -> new FoodException("User not found with given id", HttpStatus.BAD_REQUEST));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserUpdateDto userUpdateDto) {
        Optional<User> optionalUser = userRepo.findByEmail(userUpdateDto.getEmail());
        if (optionalUser.isPresent())
            throw new FoodException("There is already user with that email", HttpStatus.BAD_REQUEST);

        userUpdateDto.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        User user = userMapper.userUpdateDtoToUser(userUpdateDto);
        user = userRepo.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) throws FoodException {
        Optional<User> optionalUser = userRepo.findByEmail(userUpdateDto.getEmail());
        if (optionalUser.isPresent() && !optionalUser.get().getId().equals(id))
            throw new FoodException("There is already user with that email", HttpStatus.BAD_REQUEST);
        User user = userRepo.findByUserId(id).orElseThrow(() -> new FoodException("User not found with given id", HttpStatus.BAD_REQUEST));

        if (!userUpdateDto.getPassword().equals(user.getPassword()))
            userUpdateDto.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

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
    public UserTokenDto login(UserLoginDto userLoginDto) throws FoodException {

        System.out.println(userLoginDto.getEmail() + " " + userLoginDto.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(),
                userLoginDto.getPassword()
        ));

        User user = userRepo.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new FoodException("User not found with given email", HttpStatus.BAD_REQUEST));
        String authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return new UserTokenDto(userMapper.userToUserDto(user), jwtService.generateToken(Map.of(jwtService.getPermissionsName(), authorities), user));
    }

    @Override
    public UserUpdateDto getEditableUserById(Long id) {
        User user = userRepo.findByUserId(id).orElseThrow(() -> new FoodException("User not found with given id", HttpStatus.BAD_REQUEST));;
        return userMapper.userToUserUpdateDto(user);
    }
}
