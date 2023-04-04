package edu.client.domain;

import edu.client.service.*;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public interface Library {
    BookService getBookManager();

    AuthorService getAuthorManager();

    PublisherService getPublisherManager();

    ObservableList<Book> getBooksData();

    ObservableList<Author> getAuthorsData();

    ObservableList<Publisher> getPublishersData();
}