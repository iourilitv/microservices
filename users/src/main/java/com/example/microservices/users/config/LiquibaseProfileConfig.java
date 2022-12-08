package com.example.microservices.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * This is strictly required for overriding default properties with properties from liquibase.properties file (see the profile in pom.xml).
 * That's because in this project some datasource properties are located in private.properties file.
 */

@Profile("liquibase")
@PropertySource(value = "classpath:liquibase.properties", ignoreResourceNotFound = true)
@Configuration
public class LiquibaseProfileConfig {
}
