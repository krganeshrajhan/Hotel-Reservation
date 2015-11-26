package com.hotel.reservation.base.model;

import com.google.appengine.api.datastore.Entity;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.base.model
 * Created by: krganeshrajhan
 * Description:
 */
public class BaseEntityImpl implements BaseEntity {

    public BaseEntityImpl() {

    }

    public BaseEntityImpl(Entity entity) {

        this.id = (String) entity.getProperty("id");
    }

    private String id;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
