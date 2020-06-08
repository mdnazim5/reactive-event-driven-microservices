package com.nazim.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class CarInfo {

    @Id
    @NotNull
    private String id;
    private String manufacturer;
    private String model;
    private Double price;
    private Integer year;
    private String color;
    private Double avgRating;

    @LastModifiedDate
    private Instant modifiedAt;
    @Version
    private Integer version;

    /**
     * Constructor required for Spring Data Mongo.
     */
    public CarInfo() {
    }

    private CarInfo(final CarInfo.Builder builder) {
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
        this.price = builder.price;
        this.year = builder.year;
        this.color = builder.color;
        this.avgRating = 0.0;
    }

    public static class Builder {
        private String manufacturer;
        private String model;
        private Double price;
        private Integer year;
        private String color;
        private Double avgRating;

        Builder() {
        }

        public CarInfo build() {
            return new CarInfo(this);
        }

        public Builder setManufacturer(final String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder setModel(final String model) {
            this.model = model;
            return this;
        }

        public Builder setPrice(final Double price) {
            this.price = price;
            return this;
        }

        public Builder setYear(final Integer year) {
            this.year = year;
            return this;
        }

        public Builder setColor(final String color) {
            this.color = color;
            return this;
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }


    public String getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Double getAvgRating() {
        return avgRating;
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

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public void setAvgRating(final Double avgRating) {
        this.avgRating = avgRating;
    }

    public void setModifiedAt(final Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "id='" + id + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", avgRating='" + avgRating + '\'' +
                '}';
    }
}
