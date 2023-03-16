package edu.client.controller;

import edu.client.MainApp;
import edu.client.model.Book;
import edu.client.utils.AlertUtils;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

public class MainController {
    @Setter
    private MainApp mainApp;

    /* main app FXML */
    @FXML
    private TableView<Book> tableBooks;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TextField filterBookField;
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
            mainApp.getLibrary().add(tempBook);
        }
    }

    @FXML
    private void handleEdit() throws Exception {
        Book selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean isSaveClicked = mainApp.showBookEditDialog(selectedBook);
            if (isSaveClicked) {
                this.showBookDetails(selectedBook);
                mainApp.getLibrary().edit(selectedBook);
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
            mainApp.getLibrary().remove(selectedBook);
        } else {
            AlertUtils.showNothingIsSelectedAlert();
        }
    }

    public void setFilteredTableBooks(MainApp mainApp) {
        this.setMainApp(mainApp);

        FilteredList<Book> filteredBooksData
                = new FilteredList<>(mainApp.getLibrary().getBooksData(), predicate -> true);

        filterBookField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBooksData.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) return true;

                String lowerCaseFilter = newValue.toLowerCase();

                return comparisonBookFields(book, lowerCaseFilter);
            });
        });

        SortedList<Book> sortedBooksData = new SortedList<>(filteredBooksData);
        sortedBooksData.comparatorProperty().bind(tableBooks.comparatorProperty());

        tableBooks.setItems(sortedBooksData);
    }

    public boolean comparisonBookFields(Book book, String lowerCaseFilter) {
        if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getSection().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getYearPub().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getOrigin().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getAuthor().getName().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getAuthor().getSurname().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getAuthor().getPatronymic().toLowerCase().contains(lowerCaseFilter)) return true;

        else if (book.getPublisher().getName().toLowerCase().contains(lowerCaseFilter)) return true;

        else return book.getPublisher().getCity().toLowerCase().contains(lowerCaseFilter);
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
