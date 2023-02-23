package edu.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.client.App;
import edu.client.dao.BookDao;
import edu.client.entity.BookEntity;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class AppController {
    public static final String API_PATH = "http://localhost:8080/api/v1/book/";
    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    static Gson gson = new Gson();
    AlertUtils alerts = new AlertUtils();
    @Setter
    private Stage primaryStage;

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
    private void initialize() {
        try {
            setBookDataFromDao();
            updateTable();
            System.out.println("initial data: " + booksData);
        } catch (IOException e) {
            alerts.showServerNotFoundAlert(primaryStage);
            Platform.exit();
        }
    }

    public void setBookDataFromDao() throws IOException {
        String response = BookDao.getBookData();
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (JsonElement element : dataArr) {
            BookEntity newBook = gson.fromJson(element.toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    private void updateTable() {
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookPublisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<>("yearPub"));
        bookKindColumn.setCellValueFactory(new PropertyValueFactory<>("kind"));
        tableBooks.setItems(booksData);
    }

    public static void updateBook(BookEntity book) throws IOException {
        BookDao.updateBook(book);
        int index = booksData.get(book);
        booksData.set(index, book);
    }

    @FXML
    public void handleAddBook() throws IOException {
        BookEntity tempBook = BookEntity.getNullObject();
        App.showBookEditDialog(tempBook);
        if (ValidationUtils.validateBook(tempBook)) {
            long ID = BookDao.sendBookAndGetData(tempBook).getId();
            tempBook.setId(ID);
            booksData.add(tempBook);
            System.out.println("added: " + tempBook);
        } else {
            alerts.showError("Ошибка в написании данных");
        }
    }

    @FXML
    public void handleDeleteBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            BookDao.deleteBook(selectedBook);
            booksData.remove(selectedBook);
        } else {
            alerts.showNothingIsSelectedAlert();
        }
    }

    @FXML
    public void handleDuplicateBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            BookEntity clonedBook = selectedBook.clone();
            //Точка отправки обьекта на сервер
            clonedBook.setId(null);
            clonedBook.setId(BookDao.sendBookAndGetData(clonedBook).getId());
            booksData.add(clonedBook);
            System.out.println("duplicated: " + clonedBook);
        } else {
            alerts.showNothingIsSelectedAlert();
        }
    }

    @FXML
    public void handleEditBook() throws IOException {
        BookEntity selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            App.showBookEditDialog(selectedBook);
            updateBook(selectedBook);
            int index =  booksData.indexOf(selectedBook);
            booksData.set(index, selectedBook);
        } else {
            alerts.showNothingIsSelectedAlert();
        }
    }
}
