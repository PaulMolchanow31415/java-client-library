package edu.client.service;

import edu.client.client.Client;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public class BookService extends AbstractService<Book> {
    public BookService(ObservableList<Book> booksData, Client<Book> bookClient) {
        super(booksData, bookClient);
    }

    public void editAuthor(Author oldAuthor, Author newAuthor) throws Exception {
        for (Book book : entitiesData) {
            if (book.getAuthor().equals(oldAuthor)) {
                book.setAuthor(newAuthor);
                entityClient.update(book);
            }
        }
    }

    public void editPublisher(Publisher oldPublisher, Publisher newPublisher) throws Exception {
        for (Book book : entitiesData) {
            if (book.getPublisher().equals(oldPublisher)) {
                book.setPublisher(newPublisher);
                entityClient.update(book);
            }
        }
    }

    public void removeAuthor(Author author) {
        for (Book book : entitiesData) {
            if (book.getAuthor().equals(author)) {
                book.setAuthor(book.getAuthor().getNullObject());
            }
        }
    }

    public void removePublisher(Publisher publisher) {
        for (Book book : entitiesData) {
            if (book.getPublisher().equals(publisher)) {
                book.setPublisher(book.getPublisher().getNullObject());
            }
        }
    }
}
