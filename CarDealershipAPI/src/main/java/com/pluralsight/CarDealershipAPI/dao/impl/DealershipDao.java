package com.pluralsight.CarDealershipAPI.dao.impl;
import com.pluralsight.CarDealershipAPI.models.Dealership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {
    private Connection connection;

    public DealershipDao(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new dealership
    public void save(Dealership dealership) throws SQLException {
        String query = "INSERT INTO dealerships (Name, Address, Phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getAddress());
            stmt.setString(3, dealership.getPhone());
            stmt.executeUpdate();
        }
    }

    // Method to retrieve a dealership by ID
    public Dealership getById(int dealershipID) throws SQLException {
        String query = "SELECT * FROM dealerships WHERE DealershipID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dealershipID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Dealership(
                            rs.getInt("DealershipID"),
                            rs.getString("Name"),
                            rs.getString("Address"),
                            rs.getString("Phone")
                    );
                }
            }
        }
        return null; // Return null if no dealership found
    }

    // Method to retrieve all dealerships
    public List<Dealership> getAll() throws SQLException {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Dealership dealership = new Dealership(
                        rs.getInt("DealershipID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone")
                );
                dealerships.add(dealership);
            }
        }
        return dealerships;
    }
}
