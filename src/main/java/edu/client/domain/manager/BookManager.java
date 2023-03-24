package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public class BookManager extends AbstractManager<Book> {
    public BookManager(ObservableList<Book> booksData, Client<Book> bookClient) {
        super(booksData, bookClient);
    }

    public void editAuthor(Author oldAuthor, Author newAuthor) {
        for (Book book : entitiesData) {
            if (book.getAuthor().equals(oldAuthor)) {
                book.setAuthor(newAuthor);
            }
        }
    }

    public void editPublisher(Publisher oldPublisher, Publisher newPublisher) {
        for (Book book : entitiesData) {
            if (book.getPublisher().equals(oldPublisher)) {
                book.setPublisher(newPublisher);
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
