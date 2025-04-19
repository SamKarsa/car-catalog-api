package com.prueba.car_catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.car_catalog.dto.request.BrandRequestDTO;
import com.prueba.car_catalog.dto.response.BrandResponseDTO;
import com.prueba.car_catalog.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    // RequestDTO to entity
    @Mapping(target = "brandId", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Brand toEntity(BrandRequestDTO brandRequestDTO);

    // Entity to responseDTO
    BrandResponseDTO toBrandResponseDTO(Brand brand);
}
