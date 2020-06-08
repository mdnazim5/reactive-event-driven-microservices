package com.nazim.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class CarRating {

    @Id
    @NotNull
    private String id;
    private String carId;
    private Double rating;
    @LastModifiedDate
    private Instant modifiedAt;
    @Version
    private Integer version;

    /**
     * Constructor required for Spring Data Mongo.
     */
    public CarRating() {
    }

    public CarRating(final String carId, final Double rating) {
        this.carId = carId;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getCarId() {
        return carId;
    }

    public Double getRating() {
        return rating;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setCarId(final String carId) {
        this.carId = carId;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }

    public void setModifiedAt(final Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }
}
