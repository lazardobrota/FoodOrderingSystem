package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import com.usermanagment.backend.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class UserMapper {

    public UserDto userToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getAddress(),
                List.of() //TODO
        );
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPermissionsBitMask(0); //TODO
        return user;
    }

    public User userUpdateDtoToUser(UserUpdateDto userUpdateDto) {
        User user = new User();
        user.setId(userUpdateDto.getId());
        user.setName(userUpdateDto.getName());
        user.setLastname(userUpdateDto.getLastname());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setAddress(userUpdateDto.getAddress());
        user.setPermissionsBitMask(0); //TODO
        return user;
    }

    public User updateUser(User user, UserUpdateDto userUpdateDto) {
        user.setName(userUpdateDto.getName());
        user.setLastname(userUpdateDto.getLastname());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setAddress(userUpdateDto.getAddress());
        user.setPermissionsBitMask(0); //TODO
        return user;
    }
}
