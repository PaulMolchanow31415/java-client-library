package edu.client.dao;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws Exception;

    Integer save(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(T t) throws Exception;
}
