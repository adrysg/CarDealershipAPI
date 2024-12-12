package com.pluralsight.CarDealershipAPI.dao.impl;

import com.pluralsight.CarDealershipAPI.models.LeaseContract;
import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDao {
    private Connection connection;

    public LeaseContractDao(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new lease contract
    public void save(LeaseContract leaseContract) throws SQLException {
        String query = "INSERT INTO leasecontract (ContractDate, CustomerName, CustomerEmail, VehicleVIN, LeaseContractExpectedFinalValue, LeaseContractLeaseFee, TotalPrice, MonthlyPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, leaseContract.getContractDate());
            stmt.setString(2, leaseContract.getCustomerName());
            stmt.setString(3, leaseContract.getCustomerEmail());
            stmt.setInt(4, leaseContract.getVehicleSold().getVehicleVin());
            stmt.setDouble(5, leaseContract.getLeaseContractExpectedFinalValue());
            stmt.setDouble(6, leaseContract.getLeaseContractLeaseFee());
            stmt.setDouble(7, leaseContract.getTotalPrice());
            stmt.setDouble(8, leaseContract.getMonthlyPayment());
            stmt.executeUpdate();
        }
    }

    // Method to retrieve a lease contract by ID
    public LeaseContract getById(int contractID) throws SQLException {
        String query = "SELECT * FROM leasecontract WHERE LeaseContractID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, contractID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VehicleVIN"), rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price"));
                    return new LeaseContract(
                            rs.getInt("LeaseContractID"),
                            rs.getString("ContractDate"),
                            rs.getString("CustomerName"),
                            rs.getString("CustomerEmail"),
                            vehicle
                    );
                }
            }
        }
        return null; // Return null if no lease contract found
    }

    // Method to retrieve all lease contracts
    public List<LeaseContract> getAll() throws SQLException {
        List<LeaseContract> leaseContract = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(rs.getInt("VehicleVIN"), rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price")); // Assuming Vehicle constructor that takes VIN/ID
                LeaseContract lc = new LeaseContract(
                        rs.getInt("LeaseContractID"),
                        rs.getString("ContractDate"),
                        rs.getString("CustomerName"),
                        rs.getString("CustomerEmail"),
                        vehicle
                );
                leaseContract.add(lc);
            }
        }
        return leaseContract;

    }
}


