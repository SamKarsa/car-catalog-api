package com.prueba.car_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.car_catalog.model.Car;
import com.prueba.car_catalog.repository.CarRepository;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }
}
