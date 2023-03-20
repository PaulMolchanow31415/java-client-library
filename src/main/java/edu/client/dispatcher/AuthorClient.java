package edu.client.dispatcher;

import edu.client.model.Author;
import edu.client.properties.AppProperties;

import java.util.List;

public class AuthorClient extends AbstractClient<Author> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("author_api");

    public AuthorClient() {
        super(API_PATH);
    }
}
