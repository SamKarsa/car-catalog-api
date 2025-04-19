package com.prueba.car_catalog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.car_catalog.dto.response.BrandResponseDTO;
import com.prueba.car_catalog.service.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public List<BrandResponseDTO> getAllBrands() {
        return brandService.getAllBrands();
    }
}
