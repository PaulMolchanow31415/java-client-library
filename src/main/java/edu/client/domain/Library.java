package edu.client.domain;

import edu.client.domain.manager.AuthorManager;
import edu.client.domain.manager.BookManager;
import edu.client.domain.manager.PublisherManager;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public interface Library {
    BookManager getBookManager();

    AuthorManager getAuthorManager();

    PublisherManager getPublisherManager();

    ObservableList<Book> getBooksData();

    ObservableList<Author> getAuthorsData();

    ObservableList<Publisher> getPublishersData();
}


/* THIS INTERFACE */

 /*
 void add(T t) throws Exception;

    void remove(T t) throws Exception;

    void edit(T t) throws Exception;

 void add(Book book) throws Exception;

    void remove(Book book) throws Exception;

    void edit(Book book) throws Exception;*/

  /*  void add(Author author) throws Exception;

    void remove(Author author) throws Exception;

    void edit(Author author) throws Exception;*/

   /* void add(Publisher publisher) throws Exception;

    void remove(Publisher publisher) throws Exception;

   void edit(Publisher publisher) throws Exception;*/

/* LIBRARY CLASS */

/*public void add(E e) throws Exception {
        if (e instanceof Book) {
            bookManager.add((Book) e);
        } else if (e instanceof Author) {
            authorManager.add((Author) e);
        } else if (e instanceof Publisher) {
            publisherManager.add((Publisher) e);
        } else {
            throw new CloneNotSupportedException("Данный класс не поддерживается: " + e.getClass());
        }
    }

    public void remove(E e) throws Exception {
        if (e instanceof Book) {
            bookManager.remove((Book) e);
        } else if (e instanceof Author) {
            authorManager.remove((Author) e);
        } else if (e instanceof Publisher) {
            publisherManager.remove((Publisher) e);
        } else {
            throw new CloneNotSupportedException("Данный класс не поддерживается: " + e.getClass());
        }
    }

    public void edit(E e) throws Exception {
        if (e instanceof Book) {
            bookManager.edit((Book) e);
        } else if (e instanceof Author) {
            authorManager.edit((Author) e);
        } else if (e instanceof Publisher) {
            publisherManager.edit((Publisher) e);
        } else {
            throw new CloneNotSupportedException("Данный класс не поддерживается: " + e.getClass());
        }
    }*/