package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Entity;
import javafx.collections.ObservableList;

public abstract class AbstractManager<E extends Entity> {
    protected final ObservableList<E> entitiesData;
    protected final Client<E> entityClient;

    public AbstractManager(ObservableList<E> entitiesData, Client<E> entityClient) {
        this.entitiesData = entitiesData;
        this.entityClient = entityClient;
    }

    public void add(E entity) throws Exception {
        int id = entityClient.save(entity);
        entity.setId(id);
        entitiesData.add(entity);
    }

    public void remove(E entity) throws Exception {
        entityClient.delete(entity.getId());
        entitiesData.remove(entity);
    }

    public void edit(E oldEntity, E newEntity) throws Exception {
        entityClient.update(newEntity);
        int index = entitiesData.indexOf(oldEntity);
        entitiesData.set(index, newEntity);
    }
}
