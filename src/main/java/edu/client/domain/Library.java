package edu.client.domain;

import edu.client.service.*;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public interface Library {
    BookService getBookService();

    AuthorService getAuthorService();

    PublisherService getPublisherService();

    ObservableList<Book> getBooksData();

    ObservableList<Author> getAuthorsData();

    ObservableList<Publisher> getPublishersData();
}