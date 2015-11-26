package com.hotel.reservation.home.controller;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hotel.reservation.config.ControllerConfig;
import com.hotel.reservation.config.ServiceConfig;
import com.hotel.reservation.config.TestConfig;
import com.hotel.reservation.home.controller.HomeController;
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

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation
 * Created by: krganeshrajhan
 * Description:
 */
public class TestHomeController {


    private LocalServiceTestHelper helper;
    private HomeController homeController;
    private RoomController roomController;


    @Before
    public void setUp() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class, ServiceConfig.class,
                TestConfig.class);

        helper = context.getBean(LocalServiceTestHelper.class);
        DatastoreService datastoreService = context.getBean(DatastoreService.class);

        RoomRepositoryImpl roomRepository = context.getBean(RoomRepositoryImpl.class);
        roomRepository.setDatastore(datastoreService);
        RoomServiceImpl roomService = context.getBean(RoomServiceImpl.class);
        roomService.setRoomRepository(roomRepository);

        roomController = context.getBean(RoomController.class);
        roomController.setRoomService(roomService);
        homeController = context.getBean(HomeController.class);
        homeController.setRoomService(roomService);


        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCreateRooms() throws ParseException {

        homeController.createRooms();
        ExtendedModelMap model = new ExtendedModelMap();
        roomController.listRoom(model);
        List<Room> rooms = (List<Room>) model.get("roomList");
        assertNotNull(rooms);
        assertSame(50, rooms.size());


    }

}
