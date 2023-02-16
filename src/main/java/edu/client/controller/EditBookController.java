package edu.client.controller;

import edu.client.entity.BookEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static edu.client.controller.AppController.booksData;
import static edu.client.controller.AppController.updateBook;

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
  private int bookId;
  private boolean okClicked = false;

  public void setDialogStage(Stage dialogStage) {
    this.editBookStage = dialogStage;
  }

  private boolean isOkClicked() {
    return okClicked;
  }

  public void setLabels(BookEntity bookObj, int bookId) {
    this.book = bookObj;
    this.bookId = bookId;

    nameField.setText(book.getTitle());
    authorField.setText(book.getAuthor());
    publisherField.setText(book.getPublisher());
    yearField.setText(book.getYearPub());
    kindField.setText(book.getKind());
  }

  @FXML
  public void handleOk() {
    if (isInputValid()) {
      book.setTitle(nameField.getText());
      book.setAuthor(authorField.getText());
      book.setPublisher(publisherField.getText());
      book.setYearPub(yearField.getText());
      book.setKind(kindField.getText());

      okClicked = true;
      editBookStage.close();
      booksData.set(bookId, book);
      updateBook(book);
    }
  }

  @FXML
  public void handleCancel() {
    editBookStage.close();
  }

  private boolean isInputValid() {
    StringBuilder errorMessage = new StringBuilder();
    String title = nameField.getText();
    String author = authorField.getText();
    String publisher = publisherField.getText();
    String kind = nameField.getText();
    String yearPub = nameField.getText();

    if (title == null || title.length() == 0) errorMessage.append("Не обнаружено название книги\n");
    if (author == null || author.length() == 0) errorMessage.append("Не обнаружен автор книги\n");
    if (publisher == null || publisher.length() == 0) errorMessage.append("Не обнаружено издание книги\n");
    if (kind == null || kind.length() == 0) errorMessage.append("Не обнаружено издание книги\n");
    if (yearPub == null || yearPub.length() == 0) errorMessage.append("Не обнаружен год издания книги\n");
    else {
      try {
        Integer.parseInt(yearPub);
      } catch (NumberFormatException e) {
        errorMessage.append("Не корректное значение года выпуска книги (должно быть целочисленным)\n");
      }
    }
    if (kind == null || kind.length() == 0) errorMessage.append("Не обнаружен жанр книги\n");

    if (errorMessage.length() == 0) return true;
    else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.initOwner(editBookStage);
      alert.setTitle("Ошибка заполнения");
      alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
      alert.setContentText(errorMessage.toString());

      alert.showAndWait();

      return false;
    }
  }
}
