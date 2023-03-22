package edu.client.client;

import edu.client.model.Book;
import edu.client.properties.AppProperties;

import java.util.List;

public class BookClient extends AbstractClient<Book> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("book_api");

    public BookClient() {
        super(API_PATH);
    }

    public Integer save(Book book) throws Exception {
        String response = httpClient.post(API + "add",
                parser.serialize(book, Book.class));

        return parser.desirialize(response, Book.class).getId();
    }

    public List<Book> getAll() throws Exception {
        String response = httpClient.get(API_PATH, "all");
        return parser.serializeToList(response, Book.class);
    }

    public void update(Book book) throws Exception {
        httpClient.put(API + "update",
                parser.serialize(book, Book.class));
    }
}
