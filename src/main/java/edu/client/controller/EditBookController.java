package edu.client.controller;

import edu.client.entity.BookEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static edu.client.controller.ApplicationController.booksData;

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

  private Stage editDialogStage;
  private BookEntity book;
  private long bookId;
  private boolean okClicked = false;

  private void setDialogStage(Stage dialogStage) {
    this.editDialogStage = dialogStage;
  }

  private boolean okClicked() {
    return okClicked;
  }

  public void setLabels(BookEntity bookArg, long bookId) {
    this.book = bookArg;
    this.bookId = bookId;

    nameField.setText(book.getTitle());
    authorField.setText(book.getAuthor());
    publisherField.setText(book.getPublisher());
    yearField.setText(book.getYearPub());
    kindField.setText(book.getKind());
  }

  public void handleOk() {
    if (isInputValid()) {
      book.setTitle(nameField.getText());
      book.setAuthor(authorField.getText());
      book.setPublisher(publisherField.getText());
      book.setYearPub(yearField.getText());
      book.setKind(kindField.getText());

      okClicked = true;
      editDialogStage.close();
      booksData.set(bookId, book);
      updateBook(book);
    }
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
  }

  private void updateBook(BookEntity book) {

  }

  public void handleCancel() {
    editDialogStage.close();
  }
}
