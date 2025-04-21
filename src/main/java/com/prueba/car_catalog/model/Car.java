package com.prueba.car_catalog.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents a car entity in the catalog.
 * Each car includes details such as model, description, price, mileage, and a
 * reference to its brand.
 * <p>
 * This class is mapped to the database table {@code CARS}.
 * </p>
 * 
 * @author SamKarsa
 */

@Data
@Entity
@Table(name = "CARS")
public class Car {
    /**
     * Unique identifier for the car.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    /**
     * Model name of the car.
     */
    @Column(nullable = false, length = 30)
    private String model;

    /**
     * Description of the car.
     */
    @Column(nullable = false, length = 100)
    private String description;

    /**
     * Price of the car.
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * Mileage of the car.
     */
    @Column(nullable = false)
    private Integer mileage;

    /**
     * Reference to the brand of the car.
     * Establishes a many-to-one relationship with the {@Link Brand} entity, meaning
     * each car is associated with one brand.
     */
    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;
}
