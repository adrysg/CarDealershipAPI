package com.pluralsight.CarDealershipAPI;

import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.util.List;

public class DataManager {

    public List<Vehicle> getVehiclesByPrice(int dealershipId, double min, double max){
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(double min, double max){
        return null;
    }

    public List<Vehicle> getVehiclesByVin(int VIN){
        return null;
    }

    public List<Vehicle> getVehiclesByType(String type){
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        return null;
    }

    public List<Vehicle> getVehicleByYear(double min, double max){
        return null;
    }

    public List<Vehicle> getVehicleByColor(String color){
        return null;
    }

    public List<Vehicle> getAllVehicles(int dealershipId){
        return null;
    }

    public void addVehicle(Vehicle vehicle){
        //insert vehicle into database
    }

    public void removeVehicle(Vehicle vehicle){
        //remove vehicle from database
    }


}
