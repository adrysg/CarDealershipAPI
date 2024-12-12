package com.pluralsight.CarDealershipAPI.models;

import com.pluralsight.CarDealershipAPI.ColorCodes;
import com.pluralsight.CarDealershipAPI.ITextEncodable;

public class Vehicle implements ITextEncodable {
    private int vehicleVin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    public Vehicle(int vehicleVin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vehicleVin = vehicleVin;
        this.year = this.year;
        this.make = this.make;
        this.model = this.model;
        this.vehicleType = this.vehicleType;
        this.color = this.color;
        this.odometer = this.odometer;
        this.price = this.price;
    }

    public void setVehicleVin(int vehicleVin) {
    }

    // Getters for all fields
    public int getVehicleVin() {
        return vehicleVin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String colorString;
        if (color.equalsIgnoreCase("Red")) {
            colorString = ColorCodes.RED + color + ColorCodes.RESET;
        } else if (color.equalsIgnoreCase("White")) {
            colorString = ColorCodes.WHITE + color + ColorCodes.RESET;
        } else if (color.equalsIgnoreCase("Blue")) {
            colorString = ColorCodes.BLUE + color + ColorCodes.RESET;
        } else {
            colorString = color;
        }
        return (this.getYear() + " " + this.getMake() + " " + this.getModel() + " [" + colorString + "]");
    }

    @Override
    public String encode() {
        return new StringBuilder()
                .append(this.getVehicleVin()).append("|")
                .append(this.getYear()).append("|")
                .append(this.getMake()).append("|")
                .append(this.getModel()).append("|")
                .append(this.getVehicleType()).append("|")
                .append(this.getColor()).append("|")
                .append(this.getOdometer()).append("|")
                .append(this.getPrice()).toString();
    }


}

