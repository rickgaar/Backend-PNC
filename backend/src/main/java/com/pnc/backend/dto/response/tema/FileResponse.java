package com.pnc.backend.dto.response.tema;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponse {
    @NotNull(message = "content cannot be null")
    private String contenido;

    @NotNull(message = "filePath cannot be null")
    private String filePath;
}
