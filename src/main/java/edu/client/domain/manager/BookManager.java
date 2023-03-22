package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.dto.BookDto;
import edu.client.model.Book;
import javafx.collections.ObservableList;

public class BookManager extends AbstractManager<Book> {
    public BookManager(ObservableList<Book> booksData, Client<Book> bookClient) {
        super(booksData, bookClient);
    }

    public void add(Book book) throws Exception {
        int id = entityClient.save(book);
        book.setId(id);
        entitiesData.add(book);
    }
}
