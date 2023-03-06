package edu.client.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.client.Launcher;
import edu.client.dao.BookDao;
import edu.client.entity.Book;
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

    @FXML
    private TableView<Book> tableBooks;
    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private void initialize() {
        try {
            serializeBooksDataFromDao();
            updateTable();
            /* print default data in db */
            System.out.println("initial data: " + booksData);
        } catch (Exception e) {
            AlertUtils.showError(e.getMessage(), String.valueOf(e.getCause()));
            Platform.exit();
        }
    }

    public void serializeBooksDataFromDao() {
        try {
            String response = BookDao.getBookData();
            JsonObject base = gson.fromJson(response, JsonObject.class);
            JsonArray dataArr = base.getAsJsonArray("data");
            for (JsonElement element : dataArr) {
                Book newBook = gson.fromJson(element.toString(), Book.class);
                booksData.add(newBook);
            }
        } catch (IOException e) {
            AlertUtils.showServerNotFoundAlert();
        }
    }

    private void updateTable() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableBooks.setItems(booksData);
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

    @FXML
    public void handleAddBook() {
        try {
            Book tempBook = Book.getNullObject();
            Launcher.showBookEditDialog(tempBook);
            ValidationUtils.validateBook(tempBook);
            tempBook.setId(BookDao.sendBookAndGetData(tempBook).getId());
            booksData.add(tempBook);
            /* debug info */
            System.out.println("added: " + tempBook);
        } catch (Exception e) {
            AlertUtils.showIncorrectFillAlert("Ошибка в написании данных");
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
                AlertUtils.showIncorrectFillAlert(e.getMessage());
            }
        } else {
            AlertUtils.showNothingIsSelectedAlert();
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
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    /* TODO ================= */
    public void handleSearch(KeyEvent keyEvent) {
        System.out.println(keyEvent.toString());
    }
}
