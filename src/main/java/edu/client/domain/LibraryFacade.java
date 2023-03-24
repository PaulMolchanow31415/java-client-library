package edu.client.domain;

import edu.client.client.AuthorClient;
import edu.client.client.BookClient;
import edu.client.client.Client;
import edu.client.client.PublisherClient;
import edu.client.domain.manager.AuthorManager;
import edu.client.domain.manager.BookManager;
import edu.client.domain.manager.PublisherManager;
import edu.client.model.Author;
import edu.client.model.Book;
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
    private static final BookManager bookManager = new BookManager(booksData, bookClient);
    private static final AuthorManager authorManager = new AuthorManager(authorsData, authorClient);
    private static final PublisherManager publisherManager = new PublisherManager(publishersData, publisherClient);

    public LibraryFacade() throws Exception {
        booksData.addAll(bookClient.getAll());
        authorsData.addAll(authorClient.getAll());
        publishersData.addAll(publisherClient.getAll());
    }

    /* GETTERS */
    public BookManager getBookManager() {
        return bookManager;
    }

    public AuthorManager getAuthorManager() {
        return authorManager;
    }

    public PublisherManager getPublisherManager() {
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
