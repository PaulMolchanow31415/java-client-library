package edu.client.controller;

import edu.client.MainApp;
import edu.client.exception.ValidationException;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class EditBookController {
    @Setter
    private MainApp mainApp;
    private Book currentBook; // store link
    @Getter
    private boolean isSaveClicked = false;
    @Setter
    private Stage editStage;

    /* edit controller FXML */
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorNameField;
    @FXML
    private TextField authorSurnameField;
    @FXML
    private TextField authorPatronymicField;
    @FXML
    private TextField publisherNameField;
    @FXML
    private TextField publisherCityField;
    @FXML
    private TextField yearPubField;
    @FXML
    private TextField sectionField;
    @FXML
    private TextArea originTextArea;
    @FXML
    private ComboBox<Author> authorComboBox;
    @FXML
    private ComboBox<Publisher> publisherComboBox;

    @FXML
    private void initialize() {
        authorComboBox.setOnAction(event -> {
            Author selected = authorComboBox.getValue();
            authorNameField.setText(selected.getName());
            authorSurnameField.setText(selected.getSurname());
            authorPatronymicField.setText(selected.getPatronymic());

//            authorNameField.setEditable(false);
//            authorSurnameField.setEditable(false);
//            authorPatronymicField.setEditable(false);
        });

        publisherComboBox.setOnAction(event -> {
            Publisher selected = publisherComboBox.getValue();
            publisherNameField.setText(selected.getName());
            publisherCityField.setText(selected.getCity());

//            publisherNameField.setEditable(false);
//            publisherCityField.setEditable(false);
        });
    }

    @FXML
    private void handleSave() {
        try {
            assembleBook();
            isSaveClicked = true;
            handleClose();

        } catch (ValidationException e) {
            AlertUtils.showIncorrectFillAlert(e.getMessage());
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
        }
    }

    @FXML
    private void handleClose() {
        editStage.close();
    }

    /* edit controller METHODS */

    public void setFields(Book bookObj) {
        this.currentBook = bookObj;

        titleField.setText(currentBook.getTitle());
        originTextArea.setText(currentBook.getOrigin());
        sectionField.setText(currentBook.getSection());
        yearPubField.setText(currentBook.getYearPub());
        authorNameField.setText(currentBook.getAuthor().getName());
        authorSurnameField.setText(currentBook.getAuthor().getSurname());
        authorPatronymicField.setText(currentBook.getAuthor().getPatronymic());
        publisherNameField.setText(currentBook.getPublisher().getName());
        publisherCityField.setText(currentBook.getPublisher().getCity());

        authorComboBox.setItems(mainApp.getLibrary().getAuthorsData());
        publisherComboBox.setItems(mainApp.getLibrary().getPublishersData());
    }

    private void assembleBook() throws ValidationException {
        Author assemblyAuthor = Author.builder()
                .name(authorNameField.getText())
                .surname(authorSurnameField.getText())
                .patronymic(authorPatronymicField.getText())
                .build();

        Publisher assemblyPublisher = Publisher.builder()
                .name(publisherNameField.getText())
                .city(publisherCityField.getText())
                .build();

        Book assembly = Book.builder()
                .title(titleField.getText())
                .section(sectionField.getText())
                .yearPub(yearPubField.getText())
                .author(assemblyAuthor)
                .publisher(assemblyPublisher)
                .origin(originTextArea.getText())
                .build();

        ValidationUtils.validate(assembly);

        currentBook.setTitle(assembly.getTitle());
        currentBook.setOrigin(assembly.getOrigin());
        currentBook.setSection(assembly.getSection());
        currentBook.setYearPub(assembly.getYearPub());
        currentBook.setAuthor(assemblyAuthor);
        currentBook.setPublisher(assemblyPublisher);
    }
}