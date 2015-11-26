package com.hotel.reservation.base.repository;

import com.google.appengine.api.datastore.Entity;
import com.hotel.reservation.base.model.BaseEntity;

import java.util.List;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.base.repository
 * Created by: krganeshrajhan
 * Description:
 */
public interface BaseRepository<T extends BaseEntity> {

    /**
     * Retrieve Entity by Id
     *
     * @param entity BaseEntity object
     * @param id identifier
     * @return Entity object
     */
    public Entity findEntityById(T entity,String id);

    /**
     *
     * Retrieve all entities
     * @param entity
     * @return
     */
    public List<Entity> getAllEntities(T entity);

    /**
     *
     * Create a new Entity
     * @param entity
     * @return
     */
    public Entity generateEntity(T entity);

    /**
     * Retrieve baseEntity by Id
     *
     * @param id
     * @param baseEntity
     * @return
     */
    public T findById(T baseEntity,String id);

    /**
     * Converts list of Entity to list of BaseEntityImpl
     *
     * @param entities
     * @return list of expense entities
     */
    public List<T> getByEntity(List<Entity> entities);

    /**
     *
     * Updates the entity to datastore
     * @param entity Entity object
     */
    public void updateEntity(Entity entity);


    /**
     *
     * removes entity from datastore
     * @param entity
     */
    public void deleteEntity(Entity entity);
}
