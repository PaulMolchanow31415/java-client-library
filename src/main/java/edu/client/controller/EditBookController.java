package edu.client.controller;

import edu.client.dao.BookDao;
import edu.client.entity.BookEntity;
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
  private TextField authorField;
  @FXML
  private TextField publisherField;
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
    authorField.setText(book.getAuthor());
    publisherField.setText(book.getPublisher());
    yearField.setText(book.getYearPub());
    kindField.setText(book.getKind());
  }

  @FXML
  public void saveBook() throws IOException {
    if (isInputValidOrShowAlert()) {
      book.setTitle(nameField.getText());
      book.setAuthor(authorField.getText());
      book.setPublisher(publisherField.getText());
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
            authorField.getText(),
            publisherField.getText(),
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
