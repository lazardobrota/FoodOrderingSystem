package com.usermanagment.backend.dto.errormessage;

import com.usermanagment.backend.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewErrorMessageDto {

    private String status;

    private LocalDateTime date;

    private String message;
}
