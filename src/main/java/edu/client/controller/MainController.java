package edu.client.controller;

import edu.client.MainApp;
import edu.client.dao.BookDao;
import edu.client.domain.Library;
import edu.client.entity.Book;
import edu.client.exception.BookValidationException;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class MainController {
    @Setter
    private Stage primaryStage;
    @Setter
    private Library library;
    @Setter
    private MainApp mainApp;

    /* main app FXML */
    @FXML
    private TableView<Book> tableBooks;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label yearPubLabel;
    @FXML
    private Label sectionLabel;
    @FXML
    private Label publisherLabel;
    @FXML
    private Label originLabel;

    @FXML
    private void initialize() {
        updateTable();

        tableBooks.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    @FXML
    private void handleAdd() throws Exception {
        Book tempBook = Book.getNullObject();
        boolean isSaveClicked = mainApp.showBookEditDialog(tempBook);
        if (isSaveClicked) {
            library.add(tempBook);
        }
    }

    @FXML
    private void handleEdit() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean isSaveClicked = mainApp.showBookEditDialog(selectedBook);
            if (isSaveClicked) {
                showBookDetails(selectedBook);
                int index = library.getBooksData().indexOf(selectedBook);
                library.edit(index, selectedBook);
            }
        } else {
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    @FXML
    private void handleDelete() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            tableBooks.getItems().remove(selectedBook);
            library.remove(selectedBook.getId());
        } else {
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    @FXML
    private void handleSearch(KeyEvent keyEvent) {
        // todo
        tableBooks.getItems().filtered()
    }

    private void updateTable() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableBooks.setItems(library.getBooksData());
    }

    private void showBookDetails(Book book) {
        if (book != null) {
            titleLabel.setText(book.getTitle());
            authorLabel.setText(book.getAuthor().getName()
                    + " " + book.getAuthor().getSurname()
                    + " " + book.getAuthor().getPatronymic());
            publisherLabel.setText(book.getPublisher().getName()
                    + " " + book.getPublisher().getCity());
            yearPubLabel.setText(book.getYearPub());
            sectionLabel.setText(book.getSection());
            originLabel.setText(book.getOrigin());
        } else {
            titleLabel.setText("");
            authorLabel.setText("");
            publisherLabel.setText("");
            yearPubLabel.setText("");
            sectionLabel.setText("");
            originLabel.setText("");
        }
    }

    // fixme
    public void serializeBooksDataFromDao() {
        try {
            booksData.addAll(BookDao.getBooksData());
        } catch (IOException e) {
            AlertUtils.showServerNotFoundAlert();
        }
    }

    public static void addBook(Book book) {
        try {
            ValidationUtils.validateBook(book);
            book.setId(BookDao.addBook(book).getId());
            booksData.add(book);
            /* debug info */
            System.out.println("added: " + book);
        } catch (Exception e) {
            AlertUtils.showIncorrectFillAlert("Ошибка в написании данных");
        }
    }

    public static void updateBook(Book book) {
        try {
            BookDao.updateBook(book);
            int bookIndex = booksData.indexOf(book);
            booksData.set(bookIndex, book);
        } catch (IOException e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
        }
    }

    public static void deleteBook(Book book) {
        try {
            BookDao.deleteBook(book);
            booksData.remove(book);
        } catch (IOException e) {
            AlertUtils.showIncorrectFillAlert(e.getMessage());
        }
    }
}
