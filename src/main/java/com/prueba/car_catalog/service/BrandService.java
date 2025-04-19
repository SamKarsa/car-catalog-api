package com.prueba.car_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.car_catalog.dto.request.BrandRequestDTO;
import com.prueba.car_catalog.dto.response.BrandResponseDTO;
import com.prueba.car_catalog.mapper.BrandMapper;
import com.prueba.car_catalog.model.Brand;
import com.prueba.car_catalog.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandResponseDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toBrandResponseDTO)
                .toList();
    }

    public BrandResponseDTO getBrandById(Integer id) {
        return brandRepository.findById(id)
                .map(brandMapper::toBrandResponseDTO)
                .orElse(null);
    }

    public BrandResponseDTO saveBrand(BrandRequestDTO brandrRequestDTO) {
        Brand brand = brandMapper.toEntity(brandrRequestDTO);
        Brand saveBrand = brandRepository.save(brand);
        return brandMapper.toBrandResponseDTO(saveBrand);
    }

    public void deleteBrandById(Integer id) {
        brandRepository.deleteById(id);
    }
}
