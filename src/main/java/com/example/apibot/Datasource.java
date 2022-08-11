package com.example.apibot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class Datasource {
    @Autowired
    Environment environment;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
//        driverManagerDataSource.setUsername("postgres");
//        driverManagerDataSource.setPassword("123");
//        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");

        driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
        driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        driverManagerDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver"));
        return driverManagerDataSource;
    }

    @Bean
    JdbcTemplate getJDBCTamplate(){
        return new JdbcTemplate(dataSource());
    }
}
