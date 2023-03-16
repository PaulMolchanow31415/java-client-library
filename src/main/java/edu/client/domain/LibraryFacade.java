package edu.client.domain;

import edu.client.dao.BookDao;
import edu.client.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibraryFacade implements Library {
    private final ObservableList<Book> booksData = FXCollections.observableArrayList();
    private static final BookDao dao = new BookDao();

    public LibraryFacade() throws Exception {
        booksData.addAll(dao.getAll());
    }

    public void add(Book book) throws Exception {
        int id = dao.save(book);
        book.setId(id);
    }

    public void remove(Book book) throws Exception {
        dao.delete(book);
    }

    public void edit(Book book) throws Exception {
        dao.update(book);
    }

    public ObservableList<Book> getBooksData() {
        return booksData;
    }
}
