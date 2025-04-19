package com.prueba.car_catalog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.car_catalog.dto.ApiResponse;
import com.prueba.car_catalog.dto.request.BrandRequestDTO;
import com.prueba.car_catalog.dto.response.BrandResponseDTO;
import com.prueba.car_catalog.service.BrandService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping
    public ApiResponse<BrandResponseDTO> createBrand(@Valid @RequestBody BrandRequestDTO brandRequestDTO) {
        BrandResponseDTO createdBrand = brandService.saveBrand(brandRequestDTO);

        return new ApiResponse<>(
                createdBrand,
                200,
                "Respuesta ok");
    }

}
