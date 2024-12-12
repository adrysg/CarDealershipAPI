package com.pluralsight.CarDealershipAPI.dao.impl;

import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private Connection connection;

    public VehicleDao(Connection connection) {
        this.connection = connection;
    }

    // Save a new vehicle to the database
    public void save(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicle (year, make, model, vehicleType, color, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, vehicle.getYear());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getVehicleType());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getOdometer());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                vehicle.setVehicleVin(generatedKeys.getInt(1)); // Set generated VIN
            }
        }
    }

    // Retrieve vehicle by VIN
    public Vehicle getById(int vehicleVIN) throws SQLException {
        String query = "SELECT * FROM vehicle WHERE VehicleVIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleVIN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vehicle(
                        rs.getInt("VehicleVIN"),
                        rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price"));
            }
        }
        return null;
    }

    // Retrieve all vehicles
    public List<Vehicle> getAll() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicle";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("VehicleVIN"),
                        rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price"));
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    // Update a vehicle record
    public void update(Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicle SET year = ?, make = ?, model = ?, vehicleType = ?, color = ?, odometer = ?, price = ? WHERE VehicleVIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicle.getYear());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getVehicleType());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getOdometer());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.setInt(8, vehicle.getVehicleVin());
            stmt.executeUpdate();
        }
    }

    // Delete a vehicle record
    public void delete(int vehicleVIN) throws SQLException {
        String query = "DELETE FROM vehicle WHERE VehicleVIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleVIN);
            stmt.executeUpdate();
        }
    }
}


