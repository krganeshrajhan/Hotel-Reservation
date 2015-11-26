package com.hotel.reservation.room.controller;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hotel.reservation.config.ControllerConfig;
import com.hotel.reservation.config.RepositoryConfig;
import com.hotel.reservation.config.ServiceConfig;
import com.hotel.reservation.config.TestConfig;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.repository.RoomRepository;
import com.hotel.reservation.room.repository.RoomRepositoryImpl;
import com.hotel.reservation.room.service.RoomService;
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
 * Package Name: com.hotel.reservation.room.controller
 * Created by: krganeshrajhan
 * Description:
 */
public class TestRoomController {

    private LocalServiceTestHelper helper;
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


        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCRUD() throws ParseException {

        // Create Room
        Room room = new Room();
        room.setFloorNo(1);
        room.setRoomType(1);
        room.setRoomNo(1);
        room.setReserved(false);
        roomController.createRoom("1","1","1","N");

        //List Room
        ExtendedModelMap model = new ExtendedModelMap();
        roomController.listRoom(model);
        List<Room> rooms = (List<Room>) model.get("roomList");
        assertNotNull(rooms);
        assertSame(1, rooms.size());
        assertSame(room.getFloorNo(),rooms.get(0).getFloorNo());
        assertSame(room.getRoomType(),rooms.get(0).getRoomType());
        assertSame(room.getRoomNo(),rooms.get(0).getRoomNo());
        assertSame(room.isReserved(),rooms.get(0).isReserved());
        String id = rooms.get(0).getId();

        //Modify Room
        roomController.modify(id,"2","1","1","N");
        room.setFloorNo(2);
        roomController.listRoom(model);
        rooms = (List<Room>) model.get("roomList");
        assertNotNull(rooms);
        assertSame(1, rooms.size());
        assertSame(room.getFloorNo(),rooms.get(0).getFloorNo());

        //Remove Room
        roomController.remove(id);
        roomController.listRoom(model);
        rooms = (List<Room>) model.get("roomList");
        assertNotNull(rooms);
        assertSame(0, rooms.size());

    }
}
