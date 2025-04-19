package com.prueba.car_catalog.controller;

import java.util.List;

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
    public List<CarResponseDTO> getAllCars() {
        return carService.getAllCars();
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
