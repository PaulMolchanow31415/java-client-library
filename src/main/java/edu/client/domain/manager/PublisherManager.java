package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

public class PublisherManager extends AbstractManager<Publisher> {
    private final ObservableList<Book> booksData;

    public PublisherManager(ObservableList<Publisher> publishersData, Client<Publisher> publisherClient, ObservableList<Book> booksData) {
        super(publishersData, publisherClient);
        this.booksData = booksData;
    }

    public void remove(@NotNull Publisher publisher) throws Exception {
        super.remove(publisher);
        for (Book book : booksData) {
            if (book.getPublisher().equals(publisher)) {
                book.setPublisher(Publisher.getNullObject());
            }
        }
    }

    public void edit(Publisher oldPublisher, @NotNull Publisher newPublisher) throws Exception {
        super.edit(oldPublisher, newPublisher);
        for (Book book : booksData) {
            if (book.getPublisher().equals(oldPublisher)) {
                book.setPublisher(newPublisher);
            }
        }
    }
}
