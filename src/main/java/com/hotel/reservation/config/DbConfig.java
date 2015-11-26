package com.hotel.reservation.config;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.config
 * Created by: krganeshrajhan
 * Description:
 */
@Configuration
public class DbConfig {

    /*@Bean
    public DataSource dataSource(){
        return
                (new EmbeddedDatabaseBuilder())
                        .addScript("classpath:com/hotel/reservation/config/testdb/schema.sql")
                        .addScript("classpath:com/hotel/reservation/config/testdb/test-data.sql")
                        .build();
    }*/


    @Bean
    public DatastoreService datasource() {
        return DatastoreServiceFactory.getDatastoreService();
    }
}
