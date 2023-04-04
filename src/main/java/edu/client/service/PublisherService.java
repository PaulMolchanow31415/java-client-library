package edu.client.service;

import edu.client.client.Client;
import edu.client.model.Publisher;
import javafx.collections.ObservableList;

public class PublisherService extends AbstractService<Publisher> {
    public PublisherService(ObservableList<Publisher> publishersData, Client<Publisher> publisherClient) {
        super(publishersData, publisherClient);
    }
}
