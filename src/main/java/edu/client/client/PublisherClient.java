package edu.client.client;

import edu.client.model.Publisher;
import edu.client.properties.AppProperties;

import java.util.List;

public class PublisherClient extends AbstractClient<Publisher> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("publisher_api");

    public PublisherClient() {
        super(API_PATH);
    }

    public List<Publisher> getAll() throws Exception {
        String response = httpClient.get(API_PATH, "all");
        return parser.serializeToArray(response, Publisher.class);
    }

    public Integer save(Publisher publisher) throws Exception {
        String response = httpClient.post(API + "add",
                parser.serialize(publisher, Publisher.class));

        return parser.desirialize(response, Publisher.class).getId();
    }

    public void update(Publisher publisher) throws Exception {
        httpClient.put(API + "update", parser.serialize(publisher, Publisher.class));
    }
}
