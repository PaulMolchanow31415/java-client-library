package edu.client.controller;

import edu.client.exception.ValidationException;
import edu.client.model.Publisher;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class EditPublisherController {
    private Publisher currentPublisher; // store link
    @Getter
    private boolean isSaveClicked = false;
    @Setter
    private Stage editPublisherStage;

    @FXML
    private TextField nameField;
    @FXML
    private TextField cityField;

    @FXML
    private void handleSave() {
        try {
            assemblePublisher();
            this.isSaveClicked = true;
            handleClose();

        } catch (ValidationException e) {
            AlertUtils.showIncorrectFillAlert(e.getMessage());
        }
    }

    @FXML
    private void handleClose() {
        editPublisherStage.close();
    }

    /* edit controller METHODS */

    public void setFields(Publisher publisherObj) {
        this.currentPublisher = publisherObj;

        nameField.setText(currentPublisher.getName());
        cityField.setText(currentPublisher.getCity());
    }

    private void assemblePublisher() throws ValidationException {
        Publisher assembly = Publisher.builder()
                .name(nameField.getText())
                .city(cityField.getText())
                .build();

        ValidationUtils.validate(assembly);

        currentPublisher.setName(assembly.getName());
        currentPublisher.setCity(assembly.getCity());
    }
}
