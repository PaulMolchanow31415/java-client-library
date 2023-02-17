package edu.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.client.App;
import edu.client.entity.BookEntity;
import edu.client.utils.HTTPUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AppController {
    public static final String API_PATH = "http://localhost:8080/api/v1/book/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();

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
    private void initialize() throws IOException {
        getData();
        updateTable();
    }

    public static void getData() throws IOException {
        String response = http.get(API_PATH, "all");
        //System.out.println(res);
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (JsonElement element : dataArr) {
            BookEntity newBook = gson.fromJson(element.toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    private void updateTable() {
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        bookPublisherColumn.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("yearPub"));
        bookKindColumn.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("kind"));
        tableBooks.setItems(booksData);
    }

    public static void updateBook(BookEntity book) throws IOException {
        http.put(API_PATH + "update", gson.toJson(book));
    }

    @FXML
    public void handleAddBook() throws IOException {
        BookEntity tempBook = new BookEntity();
        booksData.add(tempBook);
        App.showBookEditDialog(tempBook, booksData.size() - 1);
        handleAddBook(tempBook);
    }

    @FXML
    public void handleDeleteBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            http.delete(API_PATH, selectedBook.getId());
            booksData.remove(selectedBook);
        } else {
            showNothingIsSelectedAlert();
        }
    }

    @FXML
    public void handleDuplicateBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            handleAddBook(selectedBook);
            booksData.add(booksData.indexOf(selectedBook) + 1, selectedBook);
        } else {
            showNothingIsSelectedAlert();
        }
    }

    @FXML
    public void handleEditBook() {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            App.showBookEditDialog(selectedBook, booksData.indexOf(selectedBook));
        } else {
            showNothingIsSelectedAlert();
        }
    }

    public static void handleAddBook(BookEntity book) throws IOException {
        System.out.println("logs: " + book.toString());
        book.setId(null);
        http.post(API_PATH + "add", gson.toJson(book));
    }

    public void showNothingIsSelectedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ничего не выбрано");
        alert.setHeaderText("Отсутсвует выбранный пользователь");
        alert.setContentText("Пожалуйста выберите пользователя в таблице");
        alert.showAndWait();
    }
}