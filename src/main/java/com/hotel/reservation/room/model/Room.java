package com.hotel.reservation.room.model;

import com.hotel.reservation.base.model.BaseEntityImpl;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.room.model
 * Created by: krganeshrajhan
 * Description:
 */
public class Room extends BaseEntityImpl {

    private int floorNo;
    private int roomNo;
    private int roomType;
    private int capacity;
    private boolean reserved;


    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
