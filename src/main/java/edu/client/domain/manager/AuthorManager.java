package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Author;
import javafx.collections.ObservableList;

public class AuthorManager extends AbstractManager<Author> {
    public AuthorManager(ObservableList<Author> authorsData, Client<Author> authorClient) {
        super(authorsData, authorClient);
    }
}
