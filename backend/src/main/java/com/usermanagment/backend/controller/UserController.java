package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.UserDto;
import com.usermanagment.backend.dto.UserUpdateDto;
import com.usermanagment.backend.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {
    private final IUserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.createUser(userUpdateDto));
    }
}
