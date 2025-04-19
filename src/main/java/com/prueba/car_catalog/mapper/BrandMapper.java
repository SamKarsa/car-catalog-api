package com.prueba.car_catalog.mapper;

import org.mapstruct.Mapper;

import com.prueba.car_catalog.dto.response.BrandResponseDTO;
import com.prueba.car_catalog.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponseDTO toBrandResponseDTO(Brand brand);
}
