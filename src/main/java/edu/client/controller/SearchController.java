package edu.client.controller;

import edu.client.model.Book;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchController {
    @FXML
    private TextField filterBookField;

    public SortedList<Book> createFilteredBooks(FilteredList<Book> filteredBooksData) {
        filterBookField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredBooksData.setPredicate(book -> {
                    if (newValue == null || newValue.isEmpty()) return true;
                    String lowerCaseFilter = newValue.toLowerCase();
                    return comparisonBookFields(book, lowerCaseFilter);
                }));

        return new SortedList<>(filteredBooksData);
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
}
