package edu.client.controller;

import edu.client.dao.BookDao;
import edu.client.entity.Author;
import edu.client.entity.Book;
import edu.client.entity.Publisher;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class EditBookController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField authorNameField;
    @FXML
    private TextField authorSurnameField;
    @FXML
    private TextField publisherNameField;
    @FXML
    private TextField publisherCityField;
    @FXML
    private TextField kindField;
    @FXML
    private TextField yearField;

    private Stage editBookStage;
    private Book book;
    @Getter
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.editBookStage = dialogStage;
    }

    public void setLabels(Book bookObj) {
        this.book = bookObj;
        nameField.setText(book.getTitle());
        authorNameField.setText(book.getAuthor().getName());
        authorSurnameField.setText(book.getAuthor().getSurname());
        publisherNameField.setText(book.getPublisher().getName());
        publisherCityField.setText(book.getPublisher().getCity());
        yearField.setText(book.getYearPub());
        kindField.setText(book.getOrigin());
    }

    @FXML
    public void saveBook() throws IOException {
        if (isInputValidOrShowAlert()) {
            book.setTitle(nameField.getText());
            book.setAuthor(Author
                    .builder()
                    .name(authorNameField.getText())
                    .surname(authorSurnameField.getText()).build());
            book.setPublisher(Publisher.builder()
                    .name(publisherNameField.getText())
                    .city(publisherCityField.getText()).build());
            book.setYearPub(yearField.getText());
            book.setOrigin(kindField.getText());
            book.setId(BookDao.sendBookAndGetData(book).getId());

            okClicked = true;
            editBookStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        editBookStage.close();
    }

    private boolean isInputValidOrShowAlert() {
        String errorMessage = ValidationUtils.getErrorMessageFromBookFields(
                nameField.getText(),
                authorNameField.getText(),
                authorSurnameField.getText(),
                publisherNameField.getText(),
                publisherCityField.getText(),
                kindField.getText(),
                yearField.getText());

        if (errorMessage.length() == 0) {
            return true;
        } else {
            AlertUtils.showIncorrectFillAlert(errorMessage);
            return false;
        }
    }
}