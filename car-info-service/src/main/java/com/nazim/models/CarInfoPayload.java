package com.nazim.models;

public class CarInfoPayload {

    protected String manufacturer;
    protected String model;
    protected Double price;
    protected Integer year;
    protected String color;

    public CarInfoPayload() {
    }

    public CarInfoPayload(final String manufacturer, final String model, final Double price, final Integer year, final String color) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.year = year;
        this.color = color;
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

}
