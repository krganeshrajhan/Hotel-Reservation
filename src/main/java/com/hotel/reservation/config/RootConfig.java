package com.hotel.reservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.config
 * Created by: krganeshrajhan
 * Description:
 */
@Configuration
@Import(ServiceConfig.class)
public class RootConfig {
}
