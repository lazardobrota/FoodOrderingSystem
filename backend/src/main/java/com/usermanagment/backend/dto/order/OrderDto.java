package com.usermanagment.backend.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private String status;

    private UserDto createdBy;

    private boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

//    private List<DishDto> dishes;
}
