package edu.client.dispatcher;

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
        return super.parser.serializeToArray(response, Publisher.class);
    }
}
