package edu.client.controller;

import edu.client.MainApp;
import edu.client.domain.Library;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import edu.client.exception.BookValidationException;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class EditBookController {
    private Book currentBook;
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
    public void handleSave() {
        try {
            this.currentBook = assembleBook();
            isSaveClicked = true;

        } catch (BookValidationException e) {
            AlertUtils.showIncorrectFillAlert(e.getMessage());
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
        } finally {
            editStage.close();
        }
    }

    @FXML
    public void handleClose() {
        editStage.close();
    }

    public void setFields(Book bookObj) {
        this.currentBook = bookObj;

        titleField.setText(currentBook.getTitle());
        yearPubField.setText(currentBook.getYearPub());
        sectionField.setText(currentBook.getSection());
        originTextArea.setText(currentBook.getOrigin());
        authorNameField.setText(currentBook.getAuthor().getName());
        authorSurnameField.setText(currentBook.getAuthor().getSurname());
        authorPatronymicField.setText(currentBook.getAuthor().getPatronymic());
        publisherNameField.setText(currentBook.getPublisher().getName());
        publisherCityField.setText(currentBook.getPublisher().getCity());
    }

    private Book assembleBook() throws BookValidationException {
        //currentBook.setId(BookDao.addBook(currentBook).getId());
        Book assembly = Book.builder()
                .title(titleField.getText())
                .section(sectionField.getText())
                .yearPub(yearPubField.getText())
                .author(Author.builder()
                        .name(authorNameField.getText())
                        .surname(authorSurnameField.getText())
                        .patronymic(authorPatronymicField.getText())
                        .build())
                .publisher(Publisher.builder()
                        .name(publisherNameField.getText())
                        .city(publisherCityField.getText())
                        .build())
                .origin(originTextArea.getText())
                .build();

        ValidationUtils.validateBook(assembly);
        return assembly;
    }
}