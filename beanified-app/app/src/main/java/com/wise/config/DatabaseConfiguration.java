package com.wise.config;

import com.wise.database.DatabaseConnector;
import com.wise.repository.PaymentRepository;
import com.wise.repository.FraudReportRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "database")
    public DatabaseProperties databaseProperties() {
        return new DatabaseProperties();
    }

    @Bean
    public DataSource dataSource(DatabaseProperties properties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String url = String.format("jdbc:h2:%s:%s", properties.getMode(), properties.getName());
        dataSource.setUrl(url);
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean(initMethod = "init")
    public DatabaseConnector databaseConnector(DatabaseProperties properties) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.setUsername(properties.getUsername());
        connector.setPassword(properties.getPassword());
        connector.setName(properties.getName());
        connector.setMode(properties.getMode());
        return connector;
    }

    @Bean
    public PaymentRepository paymentRepository(DatabaseConnector databaseConnector) {
        return new PaymentRepository(databaseConnector);
    }

    //TODO: Create a FraudRepository bean

}
