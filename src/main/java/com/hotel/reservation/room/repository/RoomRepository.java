package com.hotel.reservation.room.repository;

import com.google.appengine.api.datastore.Entity;
import com.hotel.reservation.base.repository.BaseRepository;
import com.hotel.reservation.room.model.Room;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.room.repository
 * Created by: krganeshrajhan
 * Description:
 */
public interface RoomRepository extends BaseRepository<Room> {

    public String createRoom(Room room);

    public List<Room> getAllRooms();

    public List<Room> getByEntity(List<Entity> entities);

    public void replaceRoom(String id, Room newRoom);

    public void removeRoom(String id);

    public Room getRoomById(String id);

    public Entity getEntityByRoomNo(int roomNo);

    public Room getRoomByRoomNo(int roomNo);


    public List<Room> getRoomsByFloorNo(String floorNo);
}
