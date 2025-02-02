package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.user.UserDto;
import com.usermanagment.backend.dto.user.UserUpdateDto;
import com.usermanagment.backend.permission.UserPermission;
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
                UserPermission.toMap(user.getPermissionsBitMask())
        );
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPermissionsBitMask(UserPermission.toInt(userDto.getPermissions()));
        return user;
    }

    public User userUpdateDtoToUser(UserUpdateDto userUpdateDto) {
        User user = new User();
        user.setId(userUpdateDto.getId());
        user.setName(userUpdateDto.getName());
        user.setLastname(userUpdateDto.getLastname());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setPermissionsBitMask(UserPermission.toInt(userUpdateDto.getPermissions()));
        return user;
    }

    public User updateUser(User user, UserUpdateDto userUpdateDto) {
        user.setName(userUpdateDto.getName());
        user.setLastname(userUpdateDto.getLastname());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());
        user.setPermissionsBitMask(UserPermission.toInt(userUpdateDto.getPermissions()));
        return user;
    }

    public UserUpdateDto userToUserUpdateDto(User user) {
        return new UserUpdateDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                UserPermission.toMap(user.getPermissionsBitMask())
        );
    }
}
