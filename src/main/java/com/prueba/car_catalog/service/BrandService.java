package com.prueba.car_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.car_catalog.dto.request.BrandRequestDTO;
import com.prueba.car_catalog.dto.response.BrandResponseDTO;
import com.prueba.car_catalog.exception.ResourceNotFoundException;
import com.prueba.car_catalog.mapper.BrandMapper;
import com.prueba.car_catalog.model.Brand;
import com.prueba.car_catalog.repository.BrandRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Transactional(readOnly = true)
    public List<BrandResponseDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            throw new ResourceNotFoundException("No brands were found in the database");
        }
        return brands.stream()
                .map(brandMapper::toBrandResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public BrandResponseDTO getBrandById(Integer id) {
        return brandRepository.findById(id)
                .map(brandMapper::toBrandResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", id));
    }

    public BrandResponseDTO saveBrand(BrandRequestDTO brandrRequestDTO) {
        Brand brand = brandMapper.toEntity(brandrRequestDTO);
        Brand saveBrand = brandRepository.save(brand);
        return brandMapper.toBrandResponseDTO(saveBrand);
    }
}
