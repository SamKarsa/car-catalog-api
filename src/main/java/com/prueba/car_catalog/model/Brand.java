package com.prueba.car_catalog.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents a brand entity in the catalog.
 * Each brand has a unique identifier, a name, and a list of cars associated
 * with it.
 * <p>
 * This class is mapped to the database table {@code BRANDS}.
 * </p>
 * 
 * @author SamKarsa
 */
@Data
@Entity
@Table(name = "BRANDS")
public class Brand {
    /**
     * Unique identifier for the brand.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandId;

    /**
     * Name of the brand.
     */
    @Column(nullable = false, length = 20)
    private String name;

    /**
     * List of cars associated with this brand.
     * Establishes a one-to-many relationship with the {@link Car} entity.
     * The cars list will be lazily fetched and automatically cascaded for
     * operations like persist, remove, etc.
     */
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;
}
