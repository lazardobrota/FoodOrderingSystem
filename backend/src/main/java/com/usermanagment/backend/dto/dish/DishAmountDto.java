package com.usermanagment.backend.dto.dish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishAmountDto {

    private DishDto dish;

    private int amount;
}
