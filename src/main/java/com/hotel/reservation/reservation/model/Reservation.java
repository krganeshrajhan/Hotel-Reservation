package com.hotel.reservation.reservation.model;

import com.hotel.reservation.base.model.BaseEntityImpl;
import com.hotel.reservation.room.model.Room;

import java.util.Date;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.reservation.model
 * Created by: krganeshrajhan
 * Description:
 */
public class Reservation extends BaseEntityImpl {

    private Room room;
    private Date reservedDate;


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }
}
