package com.pluralsight.CarDealershipAPI.dao.impl;

import com.pluralsight.CarDealershipAPI.models.SalesContract;
import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDao {
    private Connection connection;

    public SalesContractDao(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new sales contract
    public void save(SalesContract salesContract) throws SQLException {
        String query = "INSERT INTO sales_contracts (ContractDate, CustomerName, CustomerEmail, VehicleVIN, SalesContractTaxes, SalesContractRecordingFee, SalesContractProcessingFee, TotalPrice, MonthlyPayment, isFinance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, salesContract.getDateOfContract());
            stmt.setString(2, salesContract.getCustomerName());
            stmt.setString(3, salesContract.getCustomerEmail());
            stmt.setInt(4, salesContract.getVehicleSold().getVehicleVin());
            stmt.setDouble(5, salesContract.getSalesTaxAmount());
            stmt.setDouble(6, salesContract.getRecordingFee());
            stmt.setDouble(7, salesContract.getProcessingFee());
            stmt.setDouble(8, salesContract.getTotalPrice());
            stmt.setDouble(9, salesContract.getMonthlyPayment());
            stmt.setBoolean(10, salesContract.isWantToFinance());
            stmt.executeUpdate();
        }
    }

    // Method to retrieve a sales contract by ID
    public SalesContract getById(int contractID) throws SQLException {
        String query = "SELECT * FROM sales_contracts WHERE SalesContractID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, contractID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VehicleVIN"), rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price")); // Assuming Vehicle constructor that takes VIN/ID
                    return new SalesContract(
                            rs.getInt("SalesContractID"),
                            rs.getString("ContractDate"),
                            rs.getString("CustomerName"),
                            rs.getString("CustomerEmail"),
                            vehicle,
                            rs.getBoolean("isFinance")
                    );
                }
            }
        }
        return null; // Return null if no sales contract found
    }

    // Method to retrieve all sales contracts
    public List<SalesContract> getAll() throws SQLException {
        List<SalesContract> salesContracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(rs.getInt("VehicleVIN"), rs.getInt("year"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_type"), rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price")); // Assuming Vehicle constructor that takes VIN/ID
                SalesContract salesContract = new SalesContract(
                        rs.getInt("SalesContractID"),
                        rs.getString("ContractDate"),
                        rs.getString("CustomerName"),
                        rs.getString("CustomerEmail"),
                        vehicle,
                        rs.getBoolean("isFinance")
                );
                salesContracts.add(salesContract);
            }
        }
        return salesContracts;
    }
}
