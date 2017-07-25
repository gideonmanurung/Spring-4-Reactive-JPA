package com.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Gideon D Manurung on 7/19/2017.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class HikariFactory extends HikariConfig{

    @Bean
    public HikariDataSource dataSource() throws SQLException{
        return new HikariDataSource(this);
    }


}
