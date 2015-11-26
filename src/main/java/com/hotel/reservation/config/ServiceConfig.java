package com.hotel.reservation.config;

import com.hotel.reservation.reservation.repository.ReservationRepository;
import com.hotel.reservation.reservation.repository.ReservationRepositoryImpl;
import com.hotel.reservation.reservation.service.ReservationService;
import com.hotel.reservation.reservation.service.ReservationServiceImpl;
import com.hotel.reservation.room.repository.RoomRepository;
import com.hotel.reservation.room.service.RoomService;
import com.hotel.reservation.room.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.config
 * Created by: krganeshrajhan
 * Description:
 */
@Configuration
@Import(RepositoryConfig.class)
public class ServiceConfig {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Bean
    public RoomService roomService() {

        RoomServiceImpl roomService = new RoomServiceImpl();
        roomService.setRoomRepository(roomRepository);
        return roomService;
    }

    @Bean
    public ReservationService reservationService() {

        ReservationServiceImpl reservationService = new ReservationServiceImpl();
        reservationService.setReservationRepository(reservationRepository);
        return reservationService;
    }

}
