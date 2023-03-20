package edu.client.domain.manager;

import edu.client.dispatcher.Client;
import edu.client.model.Entity;
import javafx.collections.ObservableList;

public abstract class AbstractManager<E extends Entity> {
    private final ObservableList<E> entitiesData;
    private final Client<E> entityClient;

    public AbstractManager(ObservableList<E> entitiesData, Client<E> entityClient) {
        this.entitiesData = entitiesData;
        this.entityClient = entityClient;
    }

    public void add(E entity) throws Exception {
        int id = entityClient.save(entity);
        entity.setId(id);
        entitiesData.add(id, entity);
    }

    public void remove(E entity) throws Exception {
        entityClient.delete(entity.getId());
        entitiesData.remove(entity);
    }

    public void edit(E entity) throws Exception {
        entityClient.update(entity);
        entitiesData.set(entity.getId(), entity);
    }
}
