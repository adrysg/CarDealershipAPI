package com.pluralsight.CarDealershipAPI.Configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DbConfiguration {
    private final BasicDataSource basicDataSource;


    public DbConfiguration(
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password
    ){
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    @Bean
    public DataSource dataSource(){
        return basicDataSource;
    }

}