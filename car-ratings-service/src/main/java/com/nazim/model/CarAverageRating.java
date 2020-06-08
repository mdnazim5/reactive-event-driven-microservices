package com.nazim.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarAverageRating {

    private String carId;
    private BigDecimal avgRating;

    public CarAverageRating(final String carId, final BigDecimal avgRating) {
        this.carId = carId;
        this.avgRating = avgRating;
    }

    public CarAverageRating() {
    }

    public String getCarId() {
        return carId;
    }

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRatingPrecision() {
        this.avgRating = this.avgRating.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "CarAverageRating{" +
                "carId='" + carId + '\'' +
                ", avgRating='" + avgRating + '\'' +
                '}';
    }
}
