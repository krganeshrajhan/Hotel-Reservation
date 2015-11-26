package com.hotel.reservation.room.service;

import com.hotel.reservation.room.model.Room;

import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.service
 * Created by: krganeshrajhan
 * Description:
 */
public interface RoomService {

    public String createRoom(Room room);

    public List<Room> getAllRooms();

    public void removeRoom(String id);

    public void replaceRoom(String id, Room room);

    public Room getRoomById(String id);

    public Room getRoomByRoomNo(int roomNo);

    public Room populateRoom(int floorNo, int roomNo, int roomType, String reserved);

    public List<Room> getRoomsByFloorNo(String floorNo);
}

