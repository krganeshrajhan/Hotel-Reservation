package com.hotel.reservation.room.service;

import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.service
 * Created by: krganeshrajhan
 * Description:
 */
//@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public String createRoom(Room room) {
        return getRoomRepository().createRoom(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return getRoomRepository().getAllRooms();
    }

    @Override
    public void removeRoom(String id) {
        getRoomRepository().removeRoom(id);
    }

    @Override
    public void replaceRoom(String id, Room room) {
        getRoomRepository().replaceRoom(id, room);
    }

    @Override
    public Room getRoomById(String id) {
        return getRoomRepository().getRoomById(id);
    }

    @Override
    public Room getRoomByRoomNo(int roomNo) {
        return getRoomRepository().getRoomByRoomNo(roomNo);
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room populateRoom(int floorNo, int roomNo, int roomType, String reserved) {

        Room room = new Room();
        room.setFloorNo(floorNo);
        room.setRoomNo(roomNo);
        room.setRoomType(roomType);
        room.setReserved((reserved.equals("Y") ? true : false));
        return room;
    }

    @Override
    public List<Room> getRoomsByFloorNo(String floorNo) {
        return roomRepository.getRoomsByFloorNo(floorNo);
    }
}
