package com.hotel.reservation.reservation.service;

import com.google.appengine.api.datastore.Entity;
import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.reservation.repository.ReservationRepository;
import com.hotel.reservation.room.model.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.service
 * Created by: krganeshrajhan
 * Description:
 */
public class ReservationServiceImpl implements ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public String createReservation(Reservation reservation) {
        return getReservationRepository().createReservation(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return getReservationRepository().getAllReservations();
    }

    @Override
    public List<Reservation> getByEntity(List<Entity> entities) {
        return getReservationRepository().getByEntity(entities);
    }

    @Override
    public void replaceReservation(String id, Reservation newReservation) {
        getReservationRepository().replaceReservation(id, newReservation);
    }

    @Override
    public void removeReservation(String id) {
        getReservationRepository().removeReservation(id);
    }

    @Override
    public Reservation getReservationById(String id) {
        return getReservationRepository().getReservationById(id);
    }

    @Override
    public boolean isReservationAvailable(String roomNo, String reservedDate) throws ParseException {
        return reservationRepository.isReservationAvailable(roomNo, reservedDate);
    }

    @Override
    public List<Room> getAvailableRooms(String reservedDate) throws ParseException {
        List<Room> rooms =reservationRepository.getAvailabilityByDate(reservedDate);

        return rooms;
    }

    @Override
    public List<Reservation> getReservationByFloorNo(String floorNo) {

        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Reservation> allReservations = getAllReservations();
        int floor = Integer.parseInt(floorNo);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Reservation reservation: allReservations) {
            if((reservation.getRoom().getFloorNo()==floor) && (dateFormat.format(reservation.getReservedDate()).equals(dateFormat.format(new Date())))) {
                reservations.add(reservation);
            }
        }

        return reservations;
    }

    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
