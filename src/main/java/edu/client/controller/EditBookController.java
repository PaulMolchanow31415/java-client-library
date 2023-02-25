package edu.client.controller;

import edu.client.dao.BookDao;
import edu.client.entity.AuthorEntity;
import edu.client.entity.BookEntity;
import edu.client.entity.PublisherEntity;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

import static edu.client.controller.AppController.*;

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
  private BookEntity book;
  @Getter
  private boolean okClicked = false;

  public void setDialogStage(Stage dialogStage) {
    this.editBookStage = dialogStage;
  }

  public void setLabels(BookEntity bookObj) {
    this.book = bookObj;
    nameField.setText(book.getTitle());
    authorNameField.setText(book.getAuthor().getName());
    authorSurnameField.setText(book.getAuthor().getSurname());
    publisherNameField.setText(book.getPublisher().getName());
    publisherCityField.setText(book.getPublisher().getCity());
    yearField.setText(book.getYearPub());
    kindField.setText(book.getKind());
  }

  @FXML
  public void saveBook() throws IOException {
    if (isInputValidOrShowAlert()) {
      book.setTitle(nameField.getText());
      book.setAuthor(AuthorEntity
              .builder()
              .name(authorNameField.getText())
              .surname(authorSurnameField.getText()).build());
      book.setPublisher(PublisherEntity.builder()
              .name(publisherNameField.getText())
              .city(publisherCityField.getText()).build());
      book.setYearPub(yearField.getText());
      book.setKind(kindField.getText());
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
      AlertUtils.showError(errorMessage);
      return false;
    }
  }
}
