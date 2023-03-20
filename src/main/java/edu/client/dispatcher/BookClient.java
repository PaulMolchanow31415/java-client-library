package edu.client.dispatcher;

import edu.client.model.Book;
import edu.client.properties.AppProperties;

import java.util.List;

public class BookClient extends AbstractClient<Book> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("book_api");

    public BookClient() {
        super(API_PATH);
    }

    public List<Book> getAll() throws Exception {
        String response = httpClient.get(API_PATH, "all");
        return super.parser.serializeToArray(response, Book.class);
    }
}
