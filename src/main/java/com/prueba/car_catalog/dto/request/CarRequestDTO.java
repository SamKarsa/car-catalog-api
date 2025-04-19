package com.prueba.car_catalog.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarRequestDTO {
    @NotBlank(message = "Model is required")
    @Size(max = 30, message = "Model must be less than 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]+$", message = "Model can only contain letters, numbers, spaces and hyphens")
    private String model;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 100, message = "Model must be between 10 and 100 characters")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    private Integer price;

    @NotNull(message = "Mileage is required")
    @PositiveOrZero(message = "Mileage must be a positive number or zero")
    private Integer mileage;

    @NotNull(message = "Brand ID is required")
    @Positive(message = "Id must be a positive number")
    private Integer brandId;
}