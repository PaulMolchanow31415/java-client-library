package edu.client.domain;

import edu.client.dao.BookDao;
import edu.client.entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibraryFacade implements Library {
    private ObservableList<Book> booksData = FXCollections.observableArrayList();
    private static final BookDao dao = new BookDao();

    @Override
    public Long add(Book book) throws Exception {
    }

    @Override
    public void remove(Integer id) throws Exception {
    }

    @Override
    public void edit(Integer id, Book book) throws Exception {
    }

    @Override
    public ObservableList<Book> getBooksData() {
        return booksData;
    }
}
