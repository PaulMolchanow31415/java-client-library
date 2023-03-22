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