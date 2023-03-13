package edu.client.domain;

import edu.client.entity.Book;
import javafx.collections.ObservableList;

public interface Library {
    Long add(Book book) throws Exception;

    void remove(Integer id) throws Exception;

    void edit(Integer id, Book book) throws Exception;

    ObservableList<Book> getBooksData();
}
