package com.usermanagment.backend.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usermanagment.backend.dto.dish.DishAmountDto;
import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<DishAmountDto> dishes;

    public OrderDto(Long id, String status, UserDto createdBy, boolean active, LocalDateTime createdDate) {
        this.id = id;
        this.status = status;
        this.createdBy = createdBy;
        this.active = active;
        this.createdDate = createdDate;
        dishes = new ArrayList<>();
    }
}
