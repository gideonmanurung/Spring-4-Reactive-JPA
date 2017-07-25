package com.example.util;

import com.vladmihalcea.flexypool.FlexyPoolDataSource;
import com.vladmihalcea.flexypool.config.Configuration;
import com.vladmihalcea.flexypool.strategy.IncrementPoolOnTimeoutConnectionAcquiringStrategy;
import com.vladmihalcea.flexypool.strategy.RetryConnectionAcquiringStrategy;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

/**
 * FlexyPoolConfiguration - Configuration for flexypool data source
 *
 * @author Vlad Mihalcea
 */
@org.springframework.context.annotation.Configuration
public class FlexyPoolConfiguration {

    @Autowired
    private HikariFactory poolingDataSource;
//    private HikariDataSource poolingDataSource;

    @Value("unique")
    private String uniqueId;

    @Bean
    public Configuration<HikariDataSource> configuration() throws SQLException{
        return new Configuration.Builder<HikariDataSource>(
                uniqueId,
                poolingDataSource.dataSource(),
                HikariCPPoolAdapter.FACTORY
        ).build();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public FlexyPoolDataSource dataSource() throws SQLException{
        Configuration<HikariDataSource> configuration = configuration();
        return new FlexyPoolDataSource<HikariDataSource>(configuration,
                new IncrementPoolOnTimeoutConnectionAcquiringStrategy.Factory(5),
                new RetryConnectionAcquiringStrategy.Factory(2)
        );
    }
}
