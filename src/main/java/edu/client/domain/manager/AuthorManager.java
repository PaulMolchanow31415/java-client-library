package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Author;
import edu.client.model.Book;
import javafx.collections.ObservableList;

public class AuthorManager extends AbstractManager<Author> {
    private final ObservableList<Book> booksData;

    public AuthorManager(ObservableList<Author> authorsData, Client<Author> authorClient, ObservableList<Book> booksData) {
        super(authorsData, authorClient);
        this.booksData = booksData;
    }

    public void remove(Author author) throws Exception {
        entityClient.delete(author.getId());
        entitiesData.remove(author);

        for (Book book : booksData) {
            if (book.getAuthor().equals(author)) {
                book.setAuthor(Author.getNullObject());
            }
        }
    }
}
