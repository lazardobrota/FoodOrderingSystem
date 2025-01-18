package com.usermanagment.backend.dto.errormessage;

import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {

    private Long id;

    private UserDto user;

    private LocalDateTime date;

    private String status;

    private String message;
}
