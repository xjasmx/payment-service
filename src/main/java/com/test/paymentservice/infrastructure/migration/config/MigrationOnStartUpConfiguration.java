package com.test.paymentservice.infrastructure.migration.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class MigrationOnStartUpConfiguration {

    private final DataSource dataSource;

    public MigrationOnStartUpConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void applyMigration() {
        Flyway.configure()
                .dataSource(dataSource)
                .load()
                .migrate();
    }
}
