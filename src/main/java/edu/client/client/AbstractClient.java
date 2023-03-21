package edu.client.client;

import edu.client.model.Entity;
import edu.client.utils.JsonParser;

public abstract class AbstractClient<T extends Entity> implements Client<T> {
    protected final JsonParser<T> parser = new JsonParser<>();
    protected final String API;

    public AbstractClient(String API) {
        this.API = API;
    }

    public void delete(Integer id) throws Exception {
        httpClient.delete(API + "delete/", id);
    }
}
