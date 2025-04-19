package com.prueba.car_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.prueba.car_catalog.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    // Custom query methods can be defined here if needed
}
