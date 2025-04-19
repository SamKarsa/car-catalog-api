package com.prueba.car_catalog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.car_catalog.dto.ApiResponse;
import com.prueba.car_catalog.dto.request.CarRequestDTO;
import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.model.Car;
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
    public Car getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public ApiResponse<CarResponseDTO> createCar(@Valid @RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO createdCar = carService.saveCar(carRequestDTO);

        return new ApiResponse<>(
                createdCar,
                200,
                "Respuesta ok");
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }
}
