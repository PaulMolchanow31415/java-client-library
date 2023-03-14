package edu.client.controller;

import edu.client.MainApp;
import edu.client.dao.BookDao;
import edu.client.domain.Library;
import edu.client.model.Book;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class MainController {
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
    private TextField filterField;
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
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        tableBooks.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    @FXML
    private void handleAdd() throws Exception {
        Book tempBook = Book.getNullObject();
        boolean isSaveClicked = mainApp.showBookEditDialog(tempBook);
        if (isSaveClicked) {
            this.showBookDetails(tempBook);
            /* set book id and save */
            library.add(tempBook);
        }
    }

    @FXML
    private void handleEdit() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean isSaveClicked = mainApp.showBookEditDialog(selectedBook);
            if (isSaveClicked) {
                this.showBookDetails(selectedBook);
                library.edit(selectedBook);
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
            library.remove(selectedBook);
        } else {
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    @FXML
    public void handleFilter() {
        FilteredList<Book> filteredData
                = new FilteredList<>(library.getBooksData(), predicate -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) return true;

                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getSection()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getYearPub()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getOrigin()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getAuthor()
                        .getName()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getAuthor()
                        .getSurname()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getAuthor()
                        .getPatronymic()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getPublisher()
                        .getName()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                else if (book.getPublisher()
                        .getCity()
                        .toLowerCase()
                        .contains(lowerCaseFilter)) return true;

                return false;
            });
        });

        SortedList<Book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableBooks.comparatorProperty());
        tableBooks.setItems(sortedData);
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
    /*this.updateTable();*/
        /*private void updateTable() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableBooks.setItems(library.getBooksData());
        }*/
}
