package com.prueba.car_catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.prueba.car_catalog.dto.request.CarRequestDTO;
import com.prueba.car_catalog.dto.response.CarResponseDTO;
import com.prueba.car_catalog.mapper.CarMapper;
import com.prueba.car_catalog.model.Brand;
import com.prueba.car_catalog.model.Car;
import com.prueba.car_catalog.repository.BrandRepository;
import com.prueba.car_catalog.repository.CarRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BrandRepository brandRepository;

    public List<CarResponseDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toCarResponseDTO)
                .toList();
    }

    public CarResponseDTO getCarById(Integer id) {
        return carRepository.findById(id)
                .map(carMapper::toCarResponseDTO)
                .orElse(null);
    }

    public CarResponseDTO saveCar(CarRequestDTO carRequestDTO) {
        Brand brand = brandRepository.findById(carRequestDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Car car = carMapper.toEntity(carRequestDTO);
        car.setBrand(brand);

        Car savedCar = carRepository.save(car);

        return carMapper.toCarResponseDTO(savedCar);
    }

    public List<CarResponseDTO> filterCars(
            String model,
            Integer minPrice,
            Integer maxPrice,
            Integer minMileage,
            Integer maxMileage,
            Integer brandId) {

        final Brand brand;
        if (brandId != null) {
            brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new RuntimeException("Brand not found with id: " + brandId));
        } else {
            brand = null;
        }

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

        return carRepository.findAll(spec)
                .stream()
                .map(carMapper::toCarResponseDTO)
                .toList();
    }
}
