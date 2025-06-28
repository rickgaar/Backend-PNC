package com.pnc.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponse {
    private String accessToken;
    @Builder.Default
    private String type = "Bearer";
}
