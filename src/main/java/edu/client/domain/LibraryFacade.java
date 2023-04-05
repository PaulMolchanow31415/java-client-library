package edu.client.domain;

import edu.client.client.AuthorClient;
import edu.client.client.BookClient;
import edu.client.client.Client;
import edu.client.client.PublisherClient;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.service.*;
import edu.client.model.Publisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibraryFacade implements Library {
    private static final ObservableList<Book> booksData = FXCollections.observableArrayList();
    private static final ObservableList<Author> authorsData = FXCollections.observableArrayList();
    private static final ObservableList<Publisher> publishersData = FXCollections.observableArrayList();
    private static final Client<Book> bookClient = new BookClient();
    private static final Client<Author> authorClient = new AuthorClient();
    private static final Client<Publisher> publisherClient = new PublisherClient();
    private static final BookService bookManager = new BookService(booksData, bookClient);
    private static final AuthorService authorManager = new AuthorService(authorsData, authorClient);
    private static final PublisherService publisherManager = new PublisherService(publishersData, publisherClient);

    public LibraryFacade() throws Exception {
        booksData.addAll(bookClient.getAll());
        authorsData.addAll(authorClient.getAll());
        publishersData.addAll(publisherClient.getAll());
    }

    /* GETTERS */
    public BookService getBookService() {
        return bookManager;
    }

    public AuthorService getAuthorService() {
        return authorManager;
    }

    public PublisherService getPublisherService() {
        return publisherManager;
    }

    public ObservableList<Book> getBooksData() {
        return booksData;
    }

    public ObservableList<Author> getAuthorsData() {
        return authorsData;
    }

    public ObservableList<Publisher> getPublishersData() {
        return publishersData;
    }
}
