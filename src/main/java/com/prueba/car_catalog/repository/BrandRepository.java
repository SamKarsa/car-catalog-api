package com.prueba.car_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.car_catalog.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // Custom query methods can be defined here if needed
}
