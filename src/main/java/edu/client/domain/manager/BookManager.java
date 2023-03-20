package edu.client.domain.manager;

import edu.client.dispatcher.Client;
import edu.client.model.Book;
import javafx.collections.ObservableList;

public class BookManager extends AbstractManager<Book> {
    public BookManager(ObservableList<Book> booksData, Client<Book> bookClient) {
        super(booksData, bookClient);
    }
}
