package edu.client.dispatcher;

import edu.client.model.Author;
import edu.client.properties.AppProperties;

import java.util.List;

public class AuthorClient extends AbstractClient<Author> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("author_api");

    public AuthorClient() {
        super(API_PATH);
    }

    public List<Author> getAll() throws Exception {
        String response = httpClient.get(API_PATH, "all");
//        return super.parser.fromJsonList(response, Author[].class);
        return super.parser. (response, Author[].class);
        /// https://futurestud.io/tutorials/gson-advanced-custom-serialization-part-1#:~:text=Gson%20Series%20Overview

    }

}
