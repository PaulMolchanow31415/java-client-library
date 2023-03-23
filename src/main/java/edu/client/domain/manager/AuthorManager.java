package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Author;
import edu.client.model.Book;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

public class AuthorManager extends AbstractManager<Author> {
    private final ObservableList<Book> booksData;

    public AuthorManager(ObservableList<Author> authorsData, Client<Author> authorClient, ObservableList<Book> booksData) {
        super(authorsData, authorClient);
        this.booksData = booksData;
    }

    public void remove(@NotNull Author author) throws Exception {
        super.remove(author);
        for (Book book : booksData) {
            if (book.getAuthor().equals(author)) {
                book.setAuthor(Author.getNullObject());
            }
        }
    }

    public void edit(Author oldAuthor, @NotNull Author newAuthor) throws Exception {
        super.edit(oldAuthor, newAuthor);
        for (Book book : booksData) {
            if (book.getAuthor().equals(oldAuthor)) {
                book.setAuthor(newAuthor);
            }
        }
    }
}
