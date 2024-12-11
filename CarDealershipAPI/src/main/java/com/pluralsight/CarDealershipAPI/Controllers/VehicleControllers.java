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

    public List<Vehicle> getVehicles(
            double minPrice,
            double maxPrice,
            String make,
            String model,
            int minYear,
            int maxYear,
            String color,
            int minMiles,
            int maxMiles,
            String type
    ){
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM cardealership.vehicles;");
            ResultSet results = pStatement.executeQuery())
        {
            while (results.next()){
                int vehicleVin = results.getInt("VehicleVIN");
                int year = results.getInt("Year");
                make = results.getString("Make");
                model = results.getString("Model");
                String vehicleType = results.getString("VehicleType");
                color = results.getString("Color");
                int odometer = results.getInt("Odometer");
                double price = results.getDouble("Price");

            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return vehicles;
    }

}
