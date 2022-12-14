package com.example.microservices.users.util;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainersUtils {

    public static PostgreSQLContainer<?> sqlContainer = new PostgreSQLContainer<>("postgres:14.6")
            .withDatabaseName("users")
            .withUsername("microuser")
            .withPassword("microuser")
            //Customizing db schema instead of a default schema, public, but it is not required
            .withUrlParam("currentSchema", "users_test_scheme")
            .withInitScript("db/sql/init-db-scripts/01_ddl_create-users_test_scheme.sql")
            ;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
