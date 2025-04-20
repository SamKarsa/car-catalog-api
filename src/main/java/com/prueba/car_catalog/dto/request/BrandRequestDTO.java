package com.prueba.car_catalog.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BrandRequestDTO {
    @NotBlank(message = "Brand name is required")
    @Size(max = 20, message = "Model must be less than 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]+$", message = "Model can only contain letters, numbers, spaces and hyphens")
    private String name;
}
