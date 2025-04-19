package com.prueba.car_catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.model.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "carId", source = "car.carId")
    @Mapping(target = "model", source = "car.model")
    @Mapping(target = "description", source = "car.description")
    @Mapping(target = "price", source = "car.price")
    @Mapping(target = "mileage", source = "car.mileage")
    @Mapping(target = "brand.brandId", source = "car.brand.brandId")
    @Mapping(target = "brand.name", source = "car.brand.name")
    CarResponseDTO toCarResponseDTO(Car car);
}
