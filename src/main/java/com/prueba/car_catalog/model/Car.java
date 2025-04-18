package com.prueba.car_catalog.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @Column(nullable = false, length = 30)
    private String model;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer mileage;

    // Conection many to one with brand
    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;
}
