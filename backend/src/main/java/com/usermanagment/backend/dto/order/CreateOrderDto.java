package com.usermanagment.backend.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usermanagment.backend.dto.dish.DishDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    private List<DishDto> dishes;
}
