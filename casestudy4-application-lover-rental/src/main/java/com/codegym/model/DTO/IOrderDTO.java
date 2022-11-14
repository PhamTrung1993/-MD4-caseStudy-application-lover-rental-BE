package com.codegym.model.DTO;

import java.time.LocalDateTime;

public interface IOrderDTO {
    Long id();
    LocalDateTime startTime();

    int timeRent();

    String status();
    String getUserName();

    String getProviderName();
}
