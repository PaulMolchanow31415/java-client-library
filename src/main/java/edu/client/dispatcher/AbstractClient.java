package edu.client.dispatcher;

import edu.client.model.Entity;
import edu.client.utils.JsonParser;

public abstract class AbstractClient<T extends Entity> implements Client<T> {
    protected final JsonParser<T> parser = new JsonParser<>();
    protected final String API;

    public AbstractClient(String API) {
        this.API = API;
    }

    public Integer save(T t) throws Exception {
        String response = httpClient.post(API + "add", parser.toJsonString(t));
        return parser.desirialize(response).getId();
    }

    public void update(T t) throws Exception {
        httpClient.put(API + "update", parser.toJsonString(t));
    }

    public void delete(Integer id) throws Exception {
        httpClient.delete(API + "delete/", id);
    }
}
