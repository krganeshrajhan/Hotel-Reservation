package com.hotel.reservation.room.repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.hotel.reservation.config.RepositoryConfig;
import com.hotel.reservation.config.TestConfig;
import com.hotel.reservation.room.model.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.room.repository
 * Created by: krganeshrajhan
 * Description:
 */
public class TestRoomRepository {

    private LocalServiceTestHelper helper;
    private RoomRepositoryImpl roomRepository;


    @Before
    public void setUp() {

        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryConfig.class, TestConfig.class);

        DatastoreService datastoreService = context.getBean(DatastoreService.class);
        helper = context.getBean(LocalServiceTestHelper.class);

        roomRepository = context.getBean(RoomRepositoryImpl.class);
        roomRepository.setDatastore(datastoreService);

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
        room.setRoomNo(1);
        room.setRoomType(1);
        room.setReserved(false);
        room.setId(roomRepository.createRoom(room));
        //List Room
        List<Room> rooms = roomRepository.getAllRooms();
        assertNotNull(rooms);
        assertSame(1, rooms.size());
        assertSame(room.getFloorNo(),rooms.get(0).getFloorNo());
        assertSame(room.getRoomType(),rooms.get(0).getRoomType());
        assertSame(room.getRoomNo(),rooms.get(0).getRoomNo());
        assertSame(1,rooms.get(0).getCapacity());
        assertSame(room.isReserved(),rooms.get(0).isReserved());

        //Modify Room
        Room newRoom = roomRepository.getRoomById(room.getId());
        newRoom.setFloorNo(2);
        roomRepository.replaceRoom(room.getId(), newRoom);
        rooms = roomRepository.getAllRooms();
        assertNotNull(rooms);
        assertSame(1, rooms.size());
        assertSame(newRoom.getFloorNo(),rooms.get(0).getFloorNo());

        //Remove Room
        roomRepository.removeRoom(room.getId());
        rooms = roomRepository.getAllRooms();
        assertNotNull(rooms);
        assertSame(0, rooms.size());
    }
}
