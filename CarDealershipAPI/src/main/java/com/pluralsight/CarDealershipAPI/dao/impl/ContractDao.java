package com.pluralsight.CarDealershipAPI.dao.impl;
import com.pluralsight.CarDealershipAPI.models.Contract;
import com.pluralsight.CarDealershipAPI.models.LeaseContract;
import com.pluralsight.CarDealershipAPI.models.SalesContract;
import com.pluralsight.CarDealershipAPI.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContractDao {
    private Connection connection;

    public ContractDao(Connection connection) {
        this.connection = connection;
    }

    // Retrieve all contracts from the database
    public ArrayList<Contract> getAllContracts() {
        ArrayList<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM contracts"; // Assuming a table named contracts

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String customerName = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
                int vehicleVin = rs.getInt("vehicle_vin");
                double amount = rs.getDouble("amount");
                String type = rs.getString("type");

                Vehicle vehicle = // Fetch the vehicle using vehicleVin
                        new Vehicle(vehicleVin, rs.getInt("year"), rs.getString("make"),
                                rs.getString("model"), rs.getString("vehicle_type"),
                                rs.getString("color"), rs.getInt("odometer"), rs.getDouble("price"));

                if (type.equalsIgnoreCase("sale")) {
                    boolean isFinanced = rs.getBoolean("is_financed");
                    contracts.add(new SalesContract(id, date, customerName, customerEmail, vehicle, isFinanced));
                } else if (type.equalsIgnoreCase("lease")) {
                    contracts.add(new LeaseContract(id, date, customerName, customerEmail, vehicle));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    // Save a contract to the database
    public void saveContract(Contract contract) {
        String query;
        if (contract instanceof SalesContract) {
            SalesContract salesContract = (SalesContract) contract;
            query = "INSERT INTO contracts (date, customer_name, customer_email, vehicle_vin, type, is_financed, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else {
            LeaseContract leaseContract = (LeaseContract) contract;
            query = "INSERT INTO contracts (date, customer_name, customer_email, vehicle_vin, type) VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contract.getDateOfContract());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getCustomerEmail());
            statement.setInt(4, contract.getVehicleSold().getVehicleVin());
            statement.setString(5, contract instanceof SalesContract ? "sale" : "lease");

            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                statement.setBoolean(6, salesContract.isWantToFinance());
                statement.setDouble(7, salesContract.getMonthlyPayment());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}