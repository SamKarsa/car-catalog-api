package com.prueba.car_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.mapper.CarMapper;
import com.prueba.car_catalog.model.Car;
import com.prueba.car_catalog.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarResponseDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toCarResponseDTO)
                .toList();
    }

    public Car getCarById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }
}
