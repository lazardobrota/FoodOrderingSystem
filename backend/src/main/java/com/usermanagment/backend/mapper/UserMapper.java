package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import com.usermanagment.backend.permission.Permission;
import com.usermanagment.backend.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper {

    public UserDto userToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                Permission.toMap(user.getPermissionsBitMask())
        );
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPermissionsBitMask(Permission.toInt(userDto.getPermissions()));
        return user;
    }

    public User userUpdateDtoToUser(UserUpdateDto userUpdateDto) {
        User user = new User();
        user.setId(userUpdateDto.getId());
        user.setName(userUpdateDto.getName());
        user.setLastname(userUpdateDto.getLastname());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setPermissionsBitMask(Permission.toInt(userUpdateDto.getPermissions()));
        return user;
    }

    public User updateUser(User user, UserUpdateDto userUpdateDto) {
        user.setName(userUpdateDto.getName());
        user.setLastname(userUpdateDto.getLastname());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setPermissionsBitMask(Permission.toInt(userUpdateDto.getPermissions()));
        return user;
    }

    public UserUpdateDto userToUserUpdateDto(User user) {
        return new UserUpdateDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                Permission.toMap(user.getPermissionsBitMask())
        );
    }
}
