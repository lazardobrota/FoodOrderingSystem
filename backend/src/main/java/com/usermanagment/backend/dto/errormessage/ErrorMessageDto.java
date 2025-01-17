package com.usermanagment.backend.dto.errormessage;

import com.usermanagment.backend.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {

    private Long id;

    private OrderDto order;

    private LocalDateTime date;

    private String message;
}
