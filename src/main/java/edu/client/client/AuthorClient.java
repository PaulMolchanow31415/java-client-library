package edu.client.client;

import edu.client.model.Author;
import edu.client.properties.AppProperties;

import java.util.List;

public class AuthorClient extends AbstractClient<Author> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("author_api");

    public AuthorClient() {
        super(API_PATH);
    }

    public List<Author> getAll() throws Exception {
        String response = httpClient.get(API, "all");
        return parser.serializeToList(response, Author.class);
    }

    public Integer save(Author author) throws Exception {
        String response = httpClient.post(API + "add",
                parser.serialize(author, Author.class));

        return parser.desirialize(response, Author.class).getId();
    }

    public void update(Author author) throws Exception {
        httpClient.put(API + "update", parser.serialize(author, Author.class));
    }
}
