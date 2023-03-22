package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public class PublisherManager extends AbstractManager<Publisher> {
    private final ObservableList<Book> booksData;

    public PublisherManager(ObservableList<Publisher> publishersData, Client<Publisher> publisherClient, ObservableList<Book> booksData) {
        super(publishersData, publisherClient);
        this.booksData = booksData;
    }

    public void remove(Publisher publisher) throws Exception {
        entityClient.delete(publisher.getId());
        entitiesData.remove(publisher);

        for (Book book : booksData) {
            if (book.getPublisher().equals(publisher)) {
                book.setPublisher(Publisher.getNullObject());
            }
        }
    }
}
