package com.hotel.reservation.config;

import com.hotel.reservation.home.controller.HomeController;
import com.hotel.reservation.reservation.controller.ReservationController;
import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.reservation.service.ReservationService;
import com.hotel.reservation.room.controller.RoomController;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.config
 * Created by: krganeshrajhan
 * Description:
 */
@Configuration
public class ControllerConfig {

    @Autowired
    public RoomService roomService;

    @Autowired
    public ReservationService reservationService;

    @Bean
    public RoomController roomController() {
        RoomController roomController = new RoomController();
        roomController.setRoomService(roomService);
        return roomController;
    }

    @Bean
    public ReservationController reservationController() {

        ReservationController reservationController = new ReservationController();
        reservationController.setRoomService(roomService);
        reservationController.setReservationService(reservationService);
        return reservationController;
    }

    @Bean
    public HomeController homeController() {

        HomeController homeController = new HomeController();
        homeController.setRoomService(roomService);
        return homeController;
    }
}
