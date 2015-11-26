package com.hotel.reservation.reservation.repository;

import com.google.appengine.api.datastore.Entity;
import com.hotel.reservation.base.repository.BaseRepository;
import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.room.model.Room;

import java.text.ParseException;
import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.repository
 * Created by: krganeshrajhan
 * Description:
 */
public interface ReservationRepository extends BaseRepository<Reservation> {

    public String createReservation(Reservation reservation);

    public List<Reservation> getAllReservations();

    public List<Reservation> getByEntity(List<Entity> entities);

    public void replaceReservation(String id, Reservation newReservation);

    public void removeReservation(String id);

    public Reservation getReservationById(String id);

    public boolean isReservationAvailable(String roomNo, String reservedDate) throws ParseException;

    public List<Room> getAvailabilityByDate(String reservedDate) throws ParseException;
}
