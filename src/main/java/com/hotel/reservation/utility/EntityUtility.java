/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.reservation.utility;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;

/**
 *
 * @author krganeshrajhan
 */
public class EntityUtility {

    /**
     * Convert Entity to Embedded Entity
     *
     * @param entity
     * @return
     */
    public static EmbeddedEntity getEmbeddedEntity(Entity entity) {

        EmbeddedEntity embeddedEntity = new EmbeddedEntity();
        embeddedEntity.setKey(entity.getKey());
        embeddedEntity.setPropertiesFrom(entity);

        return embeddedEntity;
    }

    public static Entity getEntity(EmbeddedEntity embeddedEntity) {

        Entity entity = new Entity(embeddedEntity.getKey());
        entity.setPropertiesFrom(embeddedEntity);

        return entity;
    }

}
