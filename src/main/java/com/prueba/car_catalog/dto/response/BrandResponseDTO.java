package com.prueba.car_catalog.dto.response;

import com.prueba.car_catalog.model.Brand;

import lombok.Data;

@Data
public class BrandResponseDTO {
    private Integer brandId;
    private String name;

    public static BrandResponseDTO fromEntity(Brand brand) {
        BrandResponseDTO brandResponseDTO = new BrandResponseDTO();
        brandResponseDTO.setBrandId(brand.getBrandId());
        brandResponseDTO.setName(brand.getName());

        return brandResponseDTO;
    }
}
