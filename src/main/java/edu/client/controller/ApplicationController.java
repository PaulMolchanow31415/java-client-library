package edu.client.controller;

import edu.client.Application;
import edu.client.entity.BookEntity;
import edu.client.response.BookListResponse;
import edu.client.utils.HTTPUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApplicationController {
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    HTTPUtils http = new HTTPUtils();

    @FXML
    private TableView<BookEntity> tableBooks;
    @FXML
    private TableColumn<BookEntity, String> bookName;
    @FXML
    private TableColumn<BookEntity, String> bookAuthor;
    @FXML
    private TableColumn<BookEntity, String> bookPublisher;
    @FXML
    private TableColumn<BookEntity, String> bookYear;
    @FXML
    private TableColumn<BookEntity, String> bookKind;
    @FXML
    private TableColumn<BookEntity, Long> bookId;

    @FXML
    private void initialize() throws Exception {
        BookListResponse books = http.get("http://localhost:8080/api/v1/book/", "all");
        booksData.addAll(books.getData());

        bookName.setCellFactory(new PropertyValueFactory<BookEntity, String>("title"));
    }

    @FXML
    public void addBook() {
        Application.showPersonEditDialog();
    }

    public void editBook() {
    }

    public void doubleBook() {
    }

    public void deleteBook() {
    }
}