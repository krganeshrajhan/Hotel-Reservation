package com.hotel.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.config
 * Created by: krganeshrajhan
 * Description:
 */
@Configuration
public class TestConfig {

    @Bean
    public LocalDatastoreServiceTestConfig localDatastoreServiceTestConfig() {

        return new LocalDatastoreServiceTestConfig();
    }

    @Bean
    public LocalServiceTestHelper helper() {

        return new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    }

}
