package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public class PublisherManager extends AbstractManager<Publisher> {
    private final ObservableList<Book> booksData;
    private final Client<Publisher> publisherClient;

    public PublisherManager(ObservableList<Publisher> publishersData, Client<Publisher> publisherClient, ObservableList<Book> booksData) {
        super(publishersData, publisherClient);
        this.booksData = booksData;
        this.publisherClient = publisherClient;
    }

    public void remove(Publisher publisher) throws Exception {
        publisherClient.delete(publisher.getId());
        for (Book book : booksData) {
            if (book.getPublisher() == publisher) {
                book.setPublisher(null);
            }
        }
    }
}
