package edu.client.domain.manager;

import edu.client.dispatcher.Client;
import edu.client.model.Author;
import edu.client.model.Book;
import javafx.collections.ObservableList;

public class AuthorManager extends AbstractManager<Author> {
    private final ObservableList<Book> booksData;
    private final Client<Author> authorClient;

    public AuthorManager(ObservableList<Author> authorsData, Client<Author> authorClient, ObservableList<Book> booksData) {
        super(authorsData, authorClient);
        this.booksData = booksData;
        this.authorClient = authorClient;
    }

    public void remove(Author author) throws Exception {
        authorClient.delete(author.getId());
        for (Book book : booksData) {
            if (book.getAuthor() == author) {
                book.setAuthor(null);
            }
        }
    }
}
