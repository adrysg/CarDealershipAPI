package com.pluralsight.CarDealershipAPI.models;

import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;


    private ArrayList<Vehicle> inventory;


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<Vehicle>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void addVehicleToInventory(Vehicle vehicleToAdd) {
        inventory.add(vehicleToAdd);

    }

    public void removeVehicleFromInventory(Vehicle vehicleToRemove) {
        for (Vehicle v : inventory) {
            if (v.getVin() == vehicleToRemove.getVin()) {
                inventory.remove(vehicleToRemove);
            }
        }
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(double min, double max) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getMake().equalsIgnoreCase(make)  && v.getModel().equalsIgnoreCase(model) ) {
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByYear(double min, double max) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                result.add(v);
            }
        }
        return result;
    }

    public Vehicle getVehicleByVIN(int vin){
        for(Vehicle v : this.inventory){
            if(v.getVin() == vin){
                return v;
            }
        }
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

}