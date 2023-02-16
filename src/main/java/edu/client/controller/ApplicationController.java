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

public class ApplicationController {
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    HTTPUtils http = new HTTPUtils();
    @FXML
    private TableView<BookEntity> tableBooks;

    @FXML
    private TableColumn<BookEntity, String> bookNameColumn;
    @FXML
    private TableColumn<BookEntity, String> bookAuthorColumn;
    @FXML
    private TableColumn<BookEntity, String> bookPublisherColumn;
    @FXML
    private TableColumn<BookEntity, String> bookYearColumn;
    @FXML
    private TableColumn<BookEntity, String> bookKindColumn;
    @FXML
    private TableColumn<BookEntity, Long> bookIdColumn;

    @FXML
    private void initialize() throws Exception {
        BookListResponse books = http.get("http://localhost:8080/api/v1/book/", "all");
        booksData.addAll(books.getData());
        System.out.println(booksData);

//        bookId.setCellFactory(new PropertyValueFactory<BookEntity, Long>("book_id"));
//        bookName.setCellFactory(new PropertyValueFactory<BookEntity, String>("title"));
//        bookAuthor.setCellFactory(new PropertyValueFactory<BookEntity, String>("author"));
//        bookPublisher.setCellFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
//        bookYear.setCellFactory(new PropertyValueFactory<BookEntity, String>("yearPub"));
//        bookKind.setCellFactory(new PropertyValueFactory<BookEntity, String>("kind"));
//        tableBooks.setItems(booksData);
    }

    @FXML
    public void addBook() {
        Application.showBookEditDialog();
    }

    @FXML
    public void editBook() {
    }

    @FXML
    public void duplicateBook() {
    }

    @FXML
    public void deleteBook() {
    }
}