package com.usermanagment.backend.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {

    private String userEmail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer permissions;

}
