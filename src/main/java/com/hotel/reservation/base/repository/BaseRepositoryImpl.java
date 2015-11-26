package com.hotel.reservation.base.repository;

import com.google.appengine.api.datastore.*;
import com.hotel.reservation.base.model.BaseEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project: Hotel-Reservation
 * Package Name: com.hotel.reservation.base.repository
 * Created by: krganeshrajhan
 * Description:
 */
public abstract class BaseRepositoryImpl<T extends BaseEntityImpl> implements BaseRepository<T>  {

    @Autowired
    protected DatastoreService datastore;

    protected String entityName;

    /**
     * @return the datastore
     */
    public DatastoreService getDatastore() {
        return datastore;
    }

    /**
     * @param datastore the datastore to set
     */
    public void setDatastore(DatastoreService datastore) {
        this.datastore = datastore;
    }

    @Override
    public Entity findEntityById(T ent,String id) {

        Entity entity = null;

        if(ent.getClass().getName() != null) {
            Query query = new Query(ent.getClass().getSimpleName());
            query.addFilter("id", Query.FilterOperator.EQUAL, id);
            PreparedQuery pq = getDatastore().prepare(query);
            entity = pq.asSingleEntity();
        }

        return entity;
    }

    protected String generateId(T ent) {
        String id = null;

        do {
            id = UUID.randomUUID().toString();

            Entity entity = findEntityById(ent,id);

            //verify duplicate
            if (entity != null) {
                id = null;
            }
        } while (id == null);

        return id;
    }

    @Override
    public List<Entity> getAllEntities(T entity) {

        Query query = new Query(entity.getClass().getSimpleName());
        List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        return entities;
    }

    @Override
    public Entity generateEntity(T baseEntity) {
        String name = baseEntity.getClass().getSimpleName();
        Key expenseKey = createKey(name,baseEntity.getId());
        Entity entity = new Entity(name, expenseKey);
        entity.setProperty("id", baseEntity.getId());
        return entity;
    }

    protected Key createKey(String entity, String id) {

        return KeyFactory.createKey(entity, id);
    }

    @Override
    public T findById(T baseEntity,String id) {

        Entity entity = findEntityById(baseEntity, id);
        List<Entity> entities = new ArrayList<Entity>();
        if (entity != null) {
            entities.add(entity);
        }

        return (entities != null && entities.size() > 0) ? getByEntity(entities).get(0) : null;
    }

    @Override
    public abstract List<T> getByEntity(List<Entity> entities);


    @Override
    public void updateEntity(Entity entity) {
        getDatastore().put(entity);
    }

    @Override
    public void deleteEntity(Entity entity) {
        getDatastore().delete(entity.getKey());
    }

}
