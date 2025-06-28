package com.pnc.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GeneralResponse {
    private String uri;
    private String message;
    private int status;
    private LocalDate time = LocalDate.now();
    private Object data;
}