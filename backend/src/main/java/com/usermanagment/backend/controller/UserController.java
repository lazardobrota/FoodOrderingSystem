package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.user.UserDto;
import com.usermanagment.backend.dto.user.UserLoginDto;
import com.usermanagment.backend.dto.user.UserTokenDto;
import com.usermanagment.backend.dto.user.UserUpdateDto;
import com.usermanagment.backend.service.IUserService;
import com.usermanagment.backend.utils.ExceptionUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.getAllUsers(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.getUserById(id)));
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.login(userLoginDto)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.createUser(userUpdateDto)));
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<UserUpdateDto> getEditableUserById(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.getEditableUserById(id)));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.updateUser(id, userUpdateDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(userService.deleteUser(id)));
    }
}
