package com.pluralsight.CarDealershipAPI;

import com.pluralsight.CarDealershipAPI.Configuration.DbConfiguration;
import com.pluralsight.CarDealershipAPI.models.Dealership;
import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private final DbConfiguration dataSource;

    public DataManager(DbConfiguration dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getVehiclesByPrice(int dealershipId, double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();) {

            try (PreparedStatement pStatement = connection.prepareStatement("SELECT vehicles.VehicleVIN, vehicles.Year, vehicles.Make, \n" +
                    "vehicles.Model, vehicles.VehicleType, vehicles.Color, \n" +
                    "vehicles.Odometer, vehicles.Price\n" +
                    "FROM cardealership.vehicles\n" +
                    "JOIN cardealership.inventory ON Vehicles.VehicleVIN = inventory.VehicleVIN\n" +
                    "WHERE inventory.DealershipID = ?\n" +
                    "AND vehicles.Price BETWEEN ? AND ?;");) {
                pStatement.setInt(1, dealershipId);
                pStatement.setDouble(2, min);
                pStatement.setDouble(3, max);

                try (ResultSet results = pStatement.executeQuery()) {
                    while (results.next()) {
                        vehicles.add(new Vehicle(
                                results.getInt("VehicleVIN"),
                                rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price")));
                    }
                }
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> getVehiclesByMileage(double min, double max) {
        return null;
    }

    public List<Vehicle> getVehiclesByVin(int VIN) {
        return null;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return null;
    }

    public List<Vehicle> getVehicleByYear(double min, double max) {
        return null;
    }

    public List<Vehicle> getVehicleByColor(String color) {
        return null;
    }

    public List<Vehicle> getAllVehicles(int dealershipId) {
        return null;
    }

    public void addVehicleToDealership(int dealershipId, Vehicle vehicle) {
        //insert vehicle into database
    }

    public void removeVehicleFromDealership(Vehicle vehicle) {
        //remove vehicle from database
    }

    public Dealership getDealership(int dealershipId) {

    }
}

