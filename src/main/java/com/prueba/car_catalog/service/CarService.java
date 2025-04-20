package com.prueba.car_catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.prueba.car_catalog.dto.request.CarRequestDTO;
import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.exception.ResourceNotFoundException;
import com.prueba.car_catalog.exception.ValidationException;
import com.prueba.car_catalog.mapper.CarMapper;
import com.prueba.car_catalog.model.Brand;
import com.prueba.car_catalog.model.Car;
import com.prueba.car_catalog.repository.BrandRepository;
import com.prueba.car_catalog.repository.CarRepository;

import jakarta.persistence.criteria.Predicate;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public List<CarResponseDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            throw new ResourceNotFoundException("No cars found in the database");
        }
        return cars.stream()
                .map(carMapper::toCarResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CarResponseDTO getCarById(Integer id) {
        return carRepository.findById(id)
                .map(carMapper::toCarResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
    }

    public CarResponseDTO saveCar(CarRequestDTO carRequestDTO) {
        Brand brand = brandRepository.findById(carRequestDTO.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand", carRequestDTO.getBrandId()));

        if (carRequestDTO.getPrice() <= 0) {
            throw new ValidationException("The price must be greater than zero");
        }

        if (carRequestDTO.getMileage() < 0) {
            throw new ValidationException("The mileage cannot be negative");
        }

        Car car = carMapper.toEntity(carRequestDTO);
        car.setBrand(brand);
        Car savedCar = carRepository.save(car);

        return carMapper.toCarResponseDTO(savedCar);
    }

    @Transactional(readOnly = true)
    public List<CarResponseDTO> filterCars(
            String model,
            Integer minPrice,
            Integer maxPrice,
            Integer minMileage,
            Integer maxMileage,
            Integer brandId) {

        // Validate consistent ranges
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new ValidationException("The minimum price cannot be greater than the maximum.");
        }

        if (minMileage != null && maxMileage != null && minMileage > maxMileage) {
            throw new ValidationException("The minimum mileage cannot be greater than the maximum");
        }

        // Validate model characters
        if (model != null && !model.matches("^[a-zA-Z0-9 \\-]+$")) {
            throw new ValidationException("The template contains invalid characters");
        }

        final Brand brand = brandId != null ? brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", brandId)) : null;

        Specification<Car> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (model != null && !model.isEmpty()) {
                predicates.add(cb.like(
                        cb.lower(root.get("model")),
                        "%" + model.toLowerCase() + "%"));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (minMileage != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("mileage"), minMileage));
            }

            if (maxMileage != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("mileage"), maxMileage));
            }

            if (brand != null) {
                predicates.add(cb.equal(root.get("brand"), brand));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<Car> filteredCars = carRepository.findAll(spec);

        if (filteredCars.isEmpty()) {
            throw new ResourceNotFoundException("No cars were found with the specified filters");
        }

        return filteredCars.stream()
                .map(carMapper::toCarResponseDTO)
                .toList();
    }
}
