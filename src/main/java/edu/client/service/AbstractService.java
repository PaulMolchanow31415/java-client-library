package edu.client.service;

import edu.client.client.Client;
import edu.client.model.Entity;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractService<E extends Entity> {
    protected final ObservableList<E> entitiesData;
    protected final Client<E> entityClient;

    public AbstractService(ObservableList<E> entitiesData, Client<E> entityClient) {
        this.entitiesData = entitiesData;
        this.entityClient = entityClient;
    }

    public void add(@NotNull E entity) throws Exception {
        int id = entityClient.save(entity);
        entity.setId(id);
        entitiesData.add(entity);
    }

    public void remove(@NotNull E entity) throws Exception {
        entityClient.delete(entity.getId());
        entitiesData.remove(entity);
    }

    public void edit(E oldEntity, @NotNull E newEntity) throws Exception {
        entityClient.update(newEntity);
        int index = entitiesData.indexOf(oldEntity);
        if (index >= 0) {
            entitiesData.set(index, newEntity);
        } else {
            entitiesData.add(newEntity);
        }
    }
}
