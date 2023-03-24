package edu.client.domain.manager;

import edu.client.client.Client;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public class PublisherManager extends AbstractManager<Publisher> {
    public PublisherManager(ObservableList<Publisher> publishersData, Client<Publisher> publisherClient) {
        super(publishersData, publisherClient);
    }
}
