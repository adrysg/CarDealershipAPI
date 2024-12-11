package com.pluralsight.CarDealershipAPI.Controllers;

import com.pluralsight.CarDealershipAPI.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleControllers {

    private DataSource dataSource;

    @Autowired
    public VehicleControllers(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();

        DataSource dataSource;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM cardealership.vehicles;");
            ResultSet results = pStatement.executeQuery())
        {
            while (results.next()){
                int vehicleVin = results.getInt("VehicleVIN");
                int year = results.getInt("Year");
                String make = results.getString("Make");
                String model = results.getString("Model");
                String vehicleType = results.getString("VehicleType");
                String color = results.getString("Color");
                int odometer = results.getInt("Odometer");
                double price = results.getDouble("Price");

            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return vehicles;
    }

}
