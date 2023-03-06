package edu.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.client.Launcher;
import edu.client.dao.BookDao;
import edu.client.entity.Author;
import edu.client.entity.Book;
import edu.client.entity.Publisher;
import edu.client.exception.BookValidationException;
import edu.client.properties.AppProperties;
import edu.client.utils.AlertUtils;
import edu.client.utils.ValidationUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

public class LauncherController {
    public static final String API_PATH = AppProperties.getInstance().getProperty("api_path");
    public static final String DEFAULT_MEDIA_TYPE = AppProperties.getInstance().getProperty("default_media_type");
    public static ObservableList<Book> booksData = FXCollections.observableArrayList();
    static Gson gson = new Gson();
    AlertUtils alerts = new AlertUtils();
    @Setter
    private Stage primaryStage;

    @FXML
    private TableView<Book> tableBooks;
    @FXML
    private TableColumn<Book, String> bookNameColumn;
    @FXML
    private TableColumn<Book, String> authorNameColumn;
    @FXML
    private TableColumn<Book, String> publisherNameColumn;
    @FXML
    private TableColumn<Book, String> bookYearColumn;
    @FXML
    private TableColumn<Book, String> bookKindColumn;

    @FXML
    private void initialize() {
        try {
            setBookDataFromDao();
            updateTable();
            System.out.println("initial data: " + booksData);
        } catch (Exception e) {
            alerts.showServerNotFoundAlert(primaryStage);
            Platform.exit();
        }
    }

    public void setBookDataFromDao() {
        try {
            String response = BookDao.getBookData();
            JsonObject base = gson.fromJson(response, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (JsonElement element : dataArr) {
                Book newBook = gson.fromJson(element.toString(), Book.class);
                booksData.add(newBook);
            }
        } catch (IOException e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
        }
    }

    private void updateTable() {
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor().getName()));
        publisherNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher().getName()));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<>("yearPub"));
        bookKindColumn.setCellValueFactory(new PropertyValueFactory<>("kind"));
        tableBooks.setItems(booksData);
    }

    public static void updateBook(Book book) {
        try {
            BookDao.updateBook(book);
            int bookIndex = booksData.indexOf(book);
            booksData.set(bookIndex, book);
        } catch (IOException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    @FXML
    public void handleAddBook() throws IOException {
        Book tempBook = Book.getNullObject();
        Launcher.showBookEditDialog(tempBook);
        try {
            ValidationUtils.validateBook(tempBook);
            tempBook.setId(BookDao.sendBookAndGetData(tempBook).getId());
            booksData.add(tempBook);
            System.out.println("added: " + tempBook);
        } catch (BookValidationException e) {
            AlertUtils.showError("Ошибка в написании данных");
        }
    }

    @FXML
    public void handleDeleteBook() {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
                BookDao.deleteBook(selectedBook);
                booksData.remove(selectedBook);
            } catch (IOException e) {
                AlertUtils.showError(e.getMessage());
            }
        } else {
            alerts.showNothingIsSelectedAlert();
        }
    }

    @FXML
    public void handleEditBook() {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Launcher.showBookEditDialog(selectedBook);
            updateBook(selectedBook);
            int index = booksData.indexOf(selectedBook);
            booksData.set(index, selectedBook);
        } else {
            alerts.showNothingIsSelectedAlert();
        }
    }

    public void searchBook(KeyEvent keyEvent) {
        System.out.println(keyEvent.toString());
    }
}
