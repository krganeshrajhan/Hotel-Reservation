package com.hotel.reservation.reservation.repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hotel.reservation.config.RepositoryConfig;
import com.hotel.reservation.config.TestConfig;
import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.repository.RoomRepository;
import com.hotel.reservation.room.repository.RoomRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.repository
 * Created by: krganeshrajhan
 * Description:
 */
public class TestReservationRepository {

    private LocalServiceTestHelper helper;
    private RoomRepositoryImpl roomRepository;
    private ReservationRepositoryImpl reservationRepository;


    @Before
    public void setUp() {

        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class, TestConfig.class);

        DatastoreService datastoreService = context.getBean(DatastoreService.class);
        helper = context.getBean(LocalServiceTestHelper.class);

        roomRepository = context.getBean(RoomRepositoryImpl.class);
        roomRepository.setDatastore(datastoreService);
        reservationRepository = context.getBean(ReservationRepositoryImpl.class);
        reservationRepository.setDatastore(datastoreService);
        reservationRepository.setRoomRepository(roomRepository);

        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }


    @Test
    public void testCRUD() {

        // Create Reservation
        Room room = new Room();
        room.setFloorNo(1);
        room.setRoomType(1);
        room.setRoomNo(1);
        room.setReserved(false);
        room.setId(roomRepository.createRoom(room));
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setReservedDate(new Date());

        reservation.setId(reservationRepository.createReservation(reservation));

        //List Reservation
        List<Reservation> reservations = reservationRepository.getAllReservations();
        assertNotNull(reservations);
        assertSame(1, reservations.size());
//        assertSame(reservation.getReservedDate(),reservations.get(0).getReservedDate());
        assertSame(room.getRoomType(),reservations.get(0).getRoom().getRoomType());
        assertSame(room.getRoomNo(),reservations.get(0).getRoom().getRoomNo());
        assertSame(room.isReserved(),reservations.get(0).getRoom().isReserved());

        //Modify Reservation
        Reservation newReservation = reservationRepository.getReservationById(reservation.getId());

        Room newRoom = new Room();
        newRoom.setFloorNo(2);
        newRoom.setRoomType(2);
        newRoom.setRoomNo(2);
        newRoom.setReserved(false);
        newRoom.setId(roomRepository.createRoom(newRoom));
        newReservation.setRoom(newRoom);
        reservationRepository.replaceReservation(reservation.getId(), newReservation);
        reservations = reservationRepository.getAllReservations();
        assertNotNull(reservations);
        assertSame(1, reservations.size());
        assertSame(newReservation.getRoom().getFloorNo(),reservations.get(0).getRoom().getFloorNo());
        assertSame(newReservation.getRoom().getRoomNo(),reservations.get(0).getRoom().getRoomNo());
        assertSame(newReservation.getRoom().getRoomType(),reservations.get(0).getRoom().getRoomType());
        assertSame(newReservation.getRoom().isReserved(),reservations.get(0).getRoom().isReserved());

        //Remove Reservation
        reservationRepository.removeReservation(reservation.getId());
        reservations = reservationRepository.getAllReservations();
        assertNotNull(reservations);
        assertSame(0, reservations.size());

    }
}
