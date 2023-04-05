package edu.client.controller;

import edu.client.MainApp;
import edu.client.domain.Library;
import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import edu.client.utils.AlertUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

public class MainController {
    @Setter
    private Library library;
    @Setter
    private MainApp mainApp;
    /* LEFT PANEL */
    @FXML
    private TableView<Book> tableBooks;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorInitialsColumn;
    @FXML
    private TableColumn<Book, String> publisherNameColumn;
    @FXML
    private TextField filterBookField;
    /* DETAILS */
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
    private Button authorDeleteButton;
    @FXML
    private Button authorEditButton;
    @FXML
    private Button publisherEditButton;
    @FXML
    private Button publisherDeleteButton;
    private final SearchController searchController = new SearchController();

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorInitialsColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAuthor().getInitials()));
        publisherNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPublisher().getLabeling()));

        tableBooks.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));

        searchController.setFilterBookField(filterBookField);
    }

    @FXML
    private void handleAddBook() throws Exception {
        Book tempBook = new Book().getNullObject();
        boolean isSaveClicked = mainApp.showBookEditDialog(tempBook);/*modifies*/

        if (isSaveClicked) {
            /* set book id and save */
            library.getPublisherService().add(tempBook.getPublisher());
            library.getAuthorService().add(tempBook.getAuthor());
            library.getBookService().add(tempBook);
            showBookDetails(tempBook);
        }
    }

    @FXML
    private void handleEditBook() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            Book newBook = SerializationUtils.clone(selectedBook);
            boolean isSaveClicked = mainApp.showBookEditDialog(newBook);

            if (isSaveClicked) {
                library.getPublisherService().edit(selectedBook.getPublisher(), newBook.getPublisher());
                library.getAuthorService().edit(selectedBook.getAuthor(), newBook.getAuthor());
                library.getBookService().edit(selectedBook, newBook);
                showBookDetails(newBook);
                enableDetailButtons();
            }
        } else {
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    @FXML
    private void handleDeleteBook() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            library.getBookService().remove(selectedBook);
        } else {
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    @FXML
    private void handleEditAuthor() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        Author bookAuthor;

        if (selectedBook == null) {
            AlertUtils.showNothingIsSelectedAlert();
            return;
        } else {
            bookAuthor = selectedBook.getAuthor();
        }

        if (bookAuthor != null) {
            Author newAuthor = SerializationUtils.clone(bookAuthor);
            boolean isUpdateClicked = mainApp.showAuthorEditDialog(newAuthor);

            if (isUpdateClicked) {
                library.getBookService().editAuthor(bookAuthor, newAuthor);
                library.getAuthorService().edit(bookAuthor, newAuthor);
                showBookDetails(selectedBook);
                tableBooks.refresh();
            }
        } else {
            AlertUtils.showNotExistingItemAlert();
        }
    }

    @FXML
    private void handleEditPublisher() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        Publisher bookPublisher;

        if (selectedBook == null) {
            AlertUtils.showNothingIsSelectedAlert();
            return;
        } else {
            bookPublisher = selectedBook.getPublisher();
        }

        if (bookPublisher != null) {
            Publisher newPublisher = SerializationUtils.clone(bookPublisher);
            boolean isUpdateClicked = mainApp.showPublisherEditDialog(newPublisher);

            if (isUpdateClicked) {
                library.getBookService().editPublisher(bookPublisher, newPublisher);
                library.getPublisherService().edit(bookPublisher, newPublisher);
                showBookDetails(selectedBook);
                tableBooks.refresh();
            }
        } else {
            AlertUtils.showNotExistingItemAlert();
        }
    }

    @FXML
    private void handleDeleteAuthor() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        Author selectedAuthor;

        if (selectedBook == null) {
            AlertUtils.showNothingIsSelectedAlert();
            return;
        } else {
            selectedAuthor = selectedBook.getAuthor();
        }

        if (selectedAuthor != null) {
            library.getBookService().removeAuthor(selectedAuthor);
            library.getAuthorService().remove(selectedAuthor);
            showBookDetails(selectedBook);
            disableAuthorDetailButtons();
            tableBooks.refresh();
        } else {
            AlertUtils.showNotExistingItemAlert();
        }
    }

    @FXML
    private void handleDeletePublisher() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        Publisher selectedPublisher;

        if (selectedBook == null) {
            AlertUtils.showNothingIsSelectedAlert();
            return;
        } else {
            selectedPublisher = selectedBook.getPublisher();
        }

        if (selectedPublisher != null) {
            library.getBookService().removePublisher(selectedPublisher);
            library.getPublisherService().remove(selectedPublisher);
            showBookDetails(selectedBook);
            disablePublisherDetailButtons();
            tableBooks.refresh();
        } else {
            AlertUtils.showNotExistingItemAlert();
        }
    }

    @FXML
    private void closeApp() {
        Platform.exit();
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
        }
    }

    public void setFilteredTableBooks() {
        FilteredList<Book> filteredBooksData = new FilteredList<>(library.getBooksData(), predicate -> true);
        SortedList<Book> sortedBooksData = searchController.createSortedBooks(filteredBooksData);
        sortedBooksData.comparatorProperty().bind(tableBooks.comparatorProperty());
        tableBooks.setItems(sortedBooksData);
    }

    private void disableAuthorDetailButtons() {
        authorDeleteButton.setDisable(true);
        authorEditButton.setDisable(true);
    }

    private void disablePublisherDetailButtons() {
        publisherDeleteButton.setDisable(true);
        publisherEditButton.setDisable(true);
    }

    private void enableDetailButtons() {
        authorDeleteButton.setDisable(false);
        authorEditButton.setDisable(false);
        publisherDeleteButton.setDisable(false);
        publisherEditButton.setDisable(false);
    }
}
