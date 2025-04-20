package com.prueba.car_catalog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.car_catalog.dto.ApiResponse;
import com.prueba.car_catalog.dto.request.CarRequestDTO;
import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.service.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllCars(
            @PageableDefault(page = 0, size = 10, sort = "carId", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<CarResponseDTO> carsPage = carService.getAllCars(pageable);

        // Custom response structure
        Map<String, Object> response = new HashMap<>();
        response.put("data", carsPage.getContent());
        response.put("pagination", Map.of(
                "totalItems", carsPage.getTotalElements(),
                "totalPages", carsPage.getTotalPages(),
                "currentPage", carsPage.getNumber(),
                "size", carsPage.getSize()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public CarResponseDTO getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CarResponseDTO>> filterCars(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minMileage,
            @RequestParam(required = false) Integer maxMileage,
            @RequestParam(required = false) Integer brandId) {

        List<CarResponseDTO> results = carService.filterCars(
                model, minPrice, maxPrice, minMileage, maxMileage, brandId);

        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ApiResponse<CarResponseDTO> createCar(@Valid @RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO createdCar = carService.saveCar(carRequestDTO);

        return new ApiResponse<>(
                createdCar,
                200,
                "Respuesta ok");
    }
}
