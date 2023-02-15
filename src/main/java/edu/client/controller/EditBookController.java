package edu.client.controller;

import edu.client.Application;
import edu.client.entity.BookEntity;
import edu.client.utils.HTTPUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditBookController {
  HTTPUtils http = new HTTPUtils();

    @FXML
    private TextField authorLabel;
    @FXML
    private TextField nameLabel;
    @FXML
    private TextField publisherLabel;
    @FXML
    private TextField kindLabel;
    @FXML
    private TextField yearLabel;

    public void addBook() {
      Application.showPersonEditDialog();
    }

    public void closeModal() {
    }
}
