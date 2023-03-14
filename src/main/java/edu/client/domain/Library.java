package edu.client.domain;

import edu.client.model.Book;
import javafx.collections.ObservableList;

public interface Library {
    void add(Book book) throws Exception;

    void remove(Book book) throws Exception;

    void edit(Book book) throws Exception;

    ObservableList<Book> getBooksData();
}
