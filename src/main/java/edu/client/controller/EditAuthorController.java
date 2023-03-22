package edu.client.controller;

import edu.client.exception.ValidationException;
import edu.client.model.Author;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class EditAuthorController {
    private Author currentAuthor; // store link
    @Getter
    private boolean isUpdateClicked = false;
    @Setter
    private Stage editAuthorStage;

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField patronymicField;

    @FXML
    private void handleUpdate() {
        try {
            assembleAuthor();
            isUpdateClicked = true;
            handleClose();

        } catch (ValidationException e) {
            AlertUtils.showIncorrectFillAlert(e.getMessage());
        }
    }

    @FXML
    private void handleClose() {
        editAuthorStage.close();
    }

    public void setFields(Author authorObj) {
        this.currentAuthor = authorObj;

        nameField.setText(currentAuthor.getName());
        surnameField.setText(currentAuthor.getSurname());
        patronymicField.setText(currentAuthor.getPatronymic());
    }

    private void assembleAuthor() throws ValidationException {
        Author assembly = Author.builder()
                .name(nameField.getText())
                .surname(surnameField.getText())
                .patronymic(patronymicField.getText())
                .build();

        ValidationUtils.validate(assembly);

        currentAuthor.setName(assembly.getName());
        currentAuthor.setSurname(assembly.getSurname());
        currentAuthor.setPatronymic(assembly.getPatronymic());
    }
}
