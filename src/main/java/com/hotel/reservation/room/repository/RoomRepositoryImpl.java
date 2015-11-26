package com.hotel.reservation.room.repository;

import com.google.appengine.api.datastore.*;
import com.hotel.reservation.base.repository.BaseRepositoryImpl;
import com.hotel.reservation.room.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.room.repository
 * Created by: krganeshrajhan
 * Description:
 */
//@Repository("roomRepository")
public class RoomRepositoryImpl extends BaseRepositoryImpl<Room> implements RoomRepository {

    private static Room room = new Room();

    @Override
    public String createRoom(Room room) {

        room.setId(generateId(room));
        Entity entity = generateEntity(room);
        populateEntity(room, entity);
        updateEntity(entity);

        return room.getId();
    }

    @Override
    public List<Room> getAllRooms() {

        return getByEntity(getAllEntities(room));
    }

    @Override
    public List<Entity> getAllEntities(Room room) {

        Query query = new Query(room.getClass().getSimpleName()).addSort("roomNo", Query.SortDirection.ASCENDING);;
        List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        return entities;
    }

    @Override
    public List<Room> getByEntity(List<Entity> entities) {

        List<Room> rooms = new ArrayList<Room>();

        for(Entity entity : entities){
            Room room = new Room();
            Long lFloorNo = (Long) entity.getProperty("floorNo");
            room.setFloorNo(lFloorNo.intValue());
            Long lRoomType = (Long) entity.getProperty("roomType");
            room.setRoomType(lRoomType.intValue());
            Long lRoomNo = (Long) entity.getProperty("roomNo");
            room.setRoomNo(lRoomNo.intValue());
            Long lCapacity = (Long) entity.getProperty("capacity");
            room.setCapacity(lCapacity.intValue());
            room.setReserved((Boolean) entity.getProperty("reserved"));
            room.setId((String) entity.getProperty("id"));
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public void replaceRoom(String id, Room newRoom) {

        Entity entity = findEntityById(newRoom, id);

        populateEntity(newRoom, entity);

        updateEntity(entity);

    }

    @Override
    public void removeRoom(String id) {

        Entity entity = findEntityById(room,id);

        deleteEntity(entity);

    }

    @Override
    public Room getRoomById(String id) {
        return findById(room,id);
    }

    @Override
    public Entity getEntityByRoomNo(int roomNo) {

        Query query = new Query("Room");
        Query.Filter roomNoFilter =
                new Query.FilterPredicate("roomNo",
                        Query.FilterOperator.EQUAL,
                        roomNo);
        query.setFilter(roomNoFilter);
        PreparedQuery pq = datastore.prepare(query);
        Entity room = pq.asSingleEntity();

        return room;
    }

    @Override
    public Room getRoomByRoomNo(int roomNo) {

        Entity entity = getEntityByRoomNo(roomNo);
        List<Entity> entities = new ArrayList<Entity>();
        if (entity != null) {
            entities.add(entity);
        }
        return (entities != null && entities.size() > 0) ? getByEntity(entities).get(0) : null;
    }

    @Override
    public List<Room> getRoomsByFloorNo(String floorNo) {

        Query query = new Query("Room");
        Query.Filter roomNoFilter =
                new Query.FilterPredicate("floorNo",
                        Query.FilterOperator.EQUAL,
                        Integer.parseInt(floorNo));
        query.setFilter(roomNoFilter);
        query.addSort("roomNo", Query.SortDirection.ASCENDING);
        System.out.println(query);
        List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        System.out.println(entities);
        return (entities != null && entities.size() > 0) ? getByEntity(entities) : null;
    }

    public void populateEntity(Room room, Entity entity) {
        entity.setProperty("floorNo", room.getFloorNo());
        entity.setProperty("roomType", room.getRoomType());
        entity.setProperty("roomNo", room.getRoomNo());
        entity.setProperty("reserved", room.isReserved());
        if(room.getRoomType()==1) {
            room.setCapacity(1);
        } else if(room.getRoomType()==2) {
            room.setCapacity(2);
        } else if(room.getRoomType()==3) {
            room.setCapacity(4);
        }
        entity.setProperty("capacity", room.getCapacity());
    }
}
