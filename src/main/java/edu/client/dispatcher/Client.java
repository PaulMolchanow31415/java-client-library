package edu.client.dispatcher;

import edu.client.utils.HttpClient;

import java.util.List;

public interface Client<T> {
    HttpClient httpClient = new HttpClient();

    List<T> getAll() throws Exception;

    /**
     * @return id of added entity
     */
    Integer save(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(Integer id) throws Exception;
}
