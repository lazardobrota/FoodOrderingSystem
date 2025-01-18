package com.usermanagment.backend.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStatusDto {

    private Long orderId;
    private String orderStatus;
    private boolean active;
}
