package com.hotel.reservation.reservation.repository;

import com.google.appengine.api.datastore.*;
import com.hotel.reservation.base.repository.BaseRepositoryImpl;
import com.hotel.reservation.reservation.model.Reservation;
import com.hotel.reservation.room.model.Room;
import com.hotel.reservation.room.repository.RoomRepository;
import com.hotel.reservation.utility.EntityUtility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.repository
 * Created by: krganeshrajhan
 * Description:
 */
public class ReservationRepositoryImpl extends BaseRepositoryImpl<Reservation> implements ReservationRepository {

    private RoomRepository roomRepository;
    private Reservation reservation = new Reservation();
    private static final String ENTITY_NAME = "Reservation";

    @Override
    public String createReservation(Reservation reservation) {
        reservation.setId(generateId(reservation));
        Entity entity = generateEntity(reservation);
        populateEntity(reservation, entity);
        updateEntity(entity);

        return reservation.getId();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return getByEntity(getAllEntities(reservation));
    }

    @Override
    public List<Reservation> getByEntity(List<Entity> entities) {

        List<Reservation> reservations = new ArrayList<Reservation>();

        for(Entity entity : entities){
            Reservation reservation = new Reservation();
            reservation.setReservedDate((Date) entity.getProperty("reservedDate"));

            EmbeddedEntity eRoom = (EmbeddedEntity) entity.getProperty("room");
            List<Entity> eRooms = new ArrayList<Entity>();
            eRooms.add(EntityUtility.getEntity(eRoom));
            System.out.println(eRoom);
            reservation.setRoom(getRoomRepository().getByEntity(eRooms).get(0));
            reservation.setId((String) entity.getProperty("id"));
            reservations.add(reservation);
        }
        return reservations;

    }

    @Override
    public void replaceReservation(String id, Reservation newReservation) {

        Entity entity = findEntityById(newReservation, id);

        populateEntity(newReservation, entity);

        updateEntity(entity);

    }

    @Override
    public void removeReservation(String id) {

        Entity entity = findEntityById(reservation,id);

        deleteEntity(entity);

    }

    @Override
    public Reservation getReservationById(String id) {
        return findById(reservation,id);
    }

    @Override
    public boolean isReservationAvailable(String roomNo, String reservedDate) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*Query query = new Query(reservation.getClass().getSimpleName());
        Query.Filter roomNoFilter = new Query.FilterPredicate("roomNo",
                        Query.FilterOperator.EQUAL,
                        Integer.parseInt(roomNo));
        Query.Filter dateFilter = new Query.FilterPredicate("reservedDate",
                Query.FilterOperator.EQUAL,
                dateFormat.parse(reservedDate));
        Query.Filter combinedFilter =
                Query.CompositeFilterOperator.and(roomNoFilter,dateFilter);

        query.setFilter(roomNoFilter);
        System.out.println(query);
        PreparedQuery pq = getDatastore().prepare(query);
        Entity entity = pq.asSingleEntity();*/


        Query query = new Query(ENTITY_NAME);
        Query.Filter roomNoFilter =
                new Query.FilterPredicate("reservedDate",
                        Query.FilterOperator.EQUAL,
                        dateFormat.parse(reservedDate));
        query.setFilter(roomNoFilter);
        List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        long lRoomNo = Long.parseLong(roomNo);

        for(Entity entity: entities) {
            EmbeddedEntity eRoom = (EmbeddedEntity) entity.getProperty("room");
            Entity roomEntity = EntityUtility.getEntity(eRoom);
            Long eRoomNo = (Long) roomEntity.getProperty("roomNo");

            if(eRoomNo==lRoomNo) {
                return false;
            }
        }
        return true;

    }

    @Override
    public List<Room> getAvailabilityByDate(String reservedDate) throws ParseException {

        List<Room> rooms = roomRepository.getAllRooms();
        Map<Long,Room> roomMap = new HashMap<Long, Room>(rooms.size());
        for(Room room : rooms) {
            roomMap.put(new Long(room.getRoomNo()),room);
        }


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Query query = new Query(ENTITY_NAME);
        Query.Filter roomNoFilter =
                new Query.FilterPredicate("reservedDate",
                        Query.FilterOperator.EQUAL,
                        dateFormat.parse(reservedDate));
        query.setFilter(roomNoFilter);
        List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        System.out.println(entities.size());
        for(Entity entity: entities) {
            EmbeddedEntity eRoom = (EmbeddedEntity) entity.getProperty("room");
            Entity roomEntity = EntityUtility.getEntity(eRoom);
            long eRoomNo =  (Long) roomEntity.getProperty("roomNo");
            System.out.println(roomMap.size());
            roomMap.remove(eRoomNo);
            System.out.println(roomMap.size());
        }

        return new ArrayList<Room>(roomMap.values());
    }

    public void populateEntity(Reservation reservation, Entity entity) {
        entity.setProperty("reservedDate", reservation.getReservedDate());
        EmbeddedEntity eRoom = EntityUtility.getEmbeddedEntity(getRoomRepository().getEntityByRoomNo(reservation.getRoom().getRoomNo()));
        entity.setProperty("room", eRoom);
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}
