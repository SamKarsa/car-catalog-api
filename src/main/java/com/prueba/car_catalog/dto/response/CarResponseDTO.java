package com.prueba.car_catalog.dto.response;

import lombok.Data;

@Data
public class CarResponseDTO {
    private Integer carId;
    private String model;
    private String description;
    private Integer price;
    private Integer mileage;
    private BrandResponseDTO brand;
}
