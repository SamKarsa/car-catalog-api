package com.prueba.car_catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.car_catalog.dto.request.CarRequestDTO;
import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.model.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {

    // RequestDTO to entity
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "carId", ignore = true)
    Car toEntity(CarRequestDTO carRequestDTO);

    // Entity to responseDTO
    @Mapping(target = "brand.brandId", source = "brand.brandId")
    @Mapping(target = "brand.name", source = "brand.name")
    CarResponseDTO toCarResponseDTO(Car car);
}
