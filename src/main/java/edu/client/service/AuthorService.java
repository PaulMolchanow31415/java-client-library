package edu.client.service;


import edu.client.client.Client;
import edu.client.model.Author;
import javafx.collections.ObservableList;

public class AuthorService extends AbstractService<Author> {
    public AuthorService(ObservableList<Author> authorsData, Client<Author> authorClient) {
        super(authorsData, authorClient);
    }
}
