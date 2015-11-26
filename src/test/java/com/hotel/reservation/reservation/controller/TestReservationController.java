package com.hotel.reservation.reservation.controller;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hotel.reservation.config.ControllerConfig;
import com.hotel.reservation.config.ServiceConfig;
import com.hotel.reservation.config.TestConfig;
import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.reservation.repository.ReservationRepositoryImpl;
import com.hotel.reservation.reservation.service.ReservationServiceImpl;
import com.hotel.reservation.room.controller.RoomController;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.repository.RoomRepositoryImpl;
import com.hotel.reservation.room.service.RoomServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.ExtendedModelMap;

import javax.swing.text.html.parser.Parser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.controller
 * Created by: krganeshrajhan
 * Description:
 */
public class TestReservationController {

    private LocalServiceTestHelper helper;
    private RoomController roomController;
    private RoomServiceImpl roomService;
    private ReservationController reservationController;


    @Before
    public void setUp() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class, ServiceConfig.class, TestConfig.class);

        DatastoreService datastoreService = context.getBean(DatastoreService.class);
        helper = context.getBean(LocalServiceTestHelper.class);

        RoomRepositoryImpl roomRepository = context.getBean(RoomRepositoryImpl.class);
        roomRepository.setDatastore(datastoreService);
        roomService = context.getBean(RoomServiceImpl.class);
        roomService.setRoomRepository(roomRepository);
        roomController = context.getBean(RoomController.class);
        roomController.setRoomService(roomService);

        ReservationRepositoryImpl reservationRepository = context.getBean(ReservationRepositoryImpl.class);
        reservationRepository.setDatastore(datastoreService);
        reservationRepository.setRoomRepository(roomRepository);
        ReservationServiceImpl reservationService = context.getBean(ReservationServiceImpl.class);
        reservationService.setReservationRepository(reservationRepository);
        reservationController = context.getBean(ReservationController.class);
        reservationController.setReservationService(reservationService);
        reservationController.setRoomService(roomService);

        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }


    @Test
    public void testCRUD() throws ParseException {

        // Create Reservation
        Room room = new Room();
        room.setFloorNo(1);
        room.setRoomType(1);
        room.setRoomNo(1);
        room.setReserved(false);
        roomController.createRoom("1","1","1","N");
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        reservation.setReservedDate(dateFormat.parse("2015-11-19"));
        reservationController.createReservation("1", "2015-11-19");

        //List Reservation
        ExtendedModelMap model = new ExtendedModelMap();
        reservationController.listReservation(model);
        List<Reservation> reservations = (List<Reservation>) model.get("reservationList");
        assertNotNull(reservations);
        assertSame(1, reservations.size());
//        assertSame(reservation.getReservedDate(),reservations.get(0).getReservedDate());
        assertSame(room.getRoomType(),reservations.get(0).getRoom().getRoomType());
        assertSame(room.getRoomNo(),reservations.get(0).getRoom().getRoomNo());
        assertSame(room.isReserved(),reservations.get(0).getRoom().isReserved());
//        assertSame(reservation.getReservedDate().toString().trim(),reservations.get(0).getReservedDate().toString().trim());
        String id = reservations.get(0).getId();

        //Modify Reservation
        Room newRoom = new Room();
        newRoom.setFloorNo(2);
        newRoom.setRoomType(2);
        newRoom.setRoomNo(2);
        newRoom.setReserved(false);
        roomController.createRoom("2", "2", "2", "N");
        reservationController.modify(id,"2","2015-11-19");
        Reservation newReservation = new Reservation();
        newReservation.setRoom(newRoom);
        newReservation.setReservedDate(dateFormat.parse("2015-11-20"));
        reservationController.listReservation(model);
        reservations = (List<Reservation>) model.get("reservationList");
        assertNotNull(reservations);
        assertSame(1, reservations.size());
        assertSame(newReservation.getRoom().getFloorNo(),reservations.get(0).getRoom().getFloorNo());
        assertSame(newReservation.getRoom().getRoomNo(),reservations.get(0).getRoom().getRoomNo());
        assertSame(newReservation.getRoom().getRoomType(),reservations.get(0).getRoom().getRoomType());
        assertSame(newReservation.getRoom().isReserved(),reservations.get(0).getRoom().isReserved());
//        assertSame(newReservation.getReservedDate().toString().trim(),reservations.get(0).getReservedDate().toString().trim());

        //Remove Reservation
        reservationController.remove(id);
        reservationController.listReservation(model);
        reservations = (List<Reservation>) model.get("reservationList");
        assertNotNull(reservations);
        assertSame(0, reservations.size());

    }


    @Test
    public void testCannotAccomodate() throws ParseException {

        int roomNo =0;
        for(int floorNo=1; floorNo<=5; floorNo++) {
            for(int studio=0; studio<2; studio++) {
                Room room = roomService.populateRoom(floorNo, ++roomNo, 1, "N");
                roomService.createRoom(room);
            }
            for(int deluxe=0; deluxe<5; deluxe++) {
                Room room = roomService.populateRoom(floorNo, ++roomNo, 2, "N");
                roomService.createRoom(room);
            }
            for(int executive=0; executive<3; executive++) {
                Room room = roomService.populateRoom(floorNo, ++roomNo, 2, "N");
                roomService.createRoom(room);
            }
        }

        ExtendedModelMap model = new ExtendedModelMap();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String viewName = reservationController.searchRooms("1",dateFormat.format(new Date()),"500",model);
        assertSame(viewName, "reservation/CannotCheckin");
    }

    @Test
    public void testallocateSameFloor()
    {


    }

    @Test
    public void testallocateDifferentFloor()
    {
        
    }

}
