package com.hotel.reservation.room.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hotel.reservation.config.RepositoryConfig;
import com.hotel.reservation.config.ServiceConfig;
import com.hotel.reservation.config.TestConfig;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.repository.RoomRepository;
import com.hotel.reservation.room.repository.RoomRepositoryImpl;
import com.hotel.reservation.room.service.RoomServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.service
 * Created by: krganeshrajhan
 * Description:
 */
public class TestRoomService {

    private RoomServiceImpl roomService;
    private LocalServiceTestHelper helper;

    @Before
    public void setUp() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class, TestConfig.class);
        DatastoreService datastoreService = context.getBean(DatastoreService.class);
        helper = context.getBean(LocalServiceTestHelper.class);

        RoomRepositoryImpl roomRepository = context.getBean(RoomRepositoryImpl.class);
        roomRepository.setDatastore(datastoreService);
        roomService = context.getBean(RoomServiceImpl.class);
        roomService.setRoomRepository(roomRepository);

        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCRUD() {

        // Create Room
        Room room = new Room();
        room.setFloorNo(1);
        room.setRoomType(1);
        room.setRoomNo(1);
        room.setReserved(false);
        room.setId(roomService.createRoom(room));

        //List Room
        List<Room> rooms = roomService.getAllRooms();
        assertNotNull(rooms);
        assertSame(1, rooms.size());
        assertSame(room.getFloorNo(),rooms.get(0).getFloorNo());
        assertSame(room.getRoomType(),rooms.get(0).getRoomType());
        assertSame(room.getRoomNo(),rooms.get(0).getRoomNo());
        assertSame(room.isReserved(),rooms.get(0).isReserved());

        //Modify Room
        Room newRoom = roomService.getRoomById(room.getId());
        newRoom.setFloorNo(2);
        roomService.replaceRoom(room.getId(), newRoom);
        rooms = roomService.getAllRooms();
        assertNotNull(rooms);
        assertSame(1, rooms.size());
        assertSame(newRoom.getFloorNo(),rooms.get(0).getFloorNo());

        //Remove Room
        roomService.removeRoom(room.getId());
        rooms = roomService.getAllRooms();
        assertNotNull(rooms);
        assertSame(0, rooms.size());

    }
}
