package com.usermanagment.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String lastname;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String email;

    @NotNull
    private Map<String, Boolean> permissions;
}
