package com.prueba.car_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.car_catalog.model.Brand;
import com.prueba.car_catalog.repository.BrandRepository;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public void deleteBrandById(Integer id) {
        brandRepository.deleteById(id);
    }
}
