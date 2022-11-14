package com.codegym.model.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private LocalDateTime startTime;
    private int timeRent;
    private String status;
    private String getUserName;
    private String getProviderName;


}
