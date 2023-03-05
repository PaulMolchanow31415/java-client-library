package edu.client.utils;

import edu.client.entity.Book;
import edu.client.exception.BookValidationException;

public class ValidationUtils {
    public static void validateBook(Book book) throws BookValidationException {
        StringBuilder errorsMessage = new StringBuilder();
        String authorRegex = "\\w{2,64}";
        String yearRegex = "^\\d{4}$";
        String title = book.getTitle();
        String authorName = book.getAuthor().getName();
        String authorSurname = book.getAuthor().getSurname();
        String publisherName = book.getPublisher().getName();
        String publisherCity = book.getPublisher().getCity();
        String kind = book.getOrigin();
        String year = book.getYearPub();

        if (title == null || title.isBlank() || title.length() < 3 || title.length() > 256) {
            errorsMessage.append("Не правильно введено заглавие\n");
        }
        if (authorName == null || authorName.isBlank() || !authorName.matches(authorRegex)) {
            errorsMessage.append("Не правильно введено имя автора\n");
        }
        if (authorSurname == null || authorSurname.isBlank() || !authorSurname.matches(authorRegex)) {
            errorsMessage.append("Не правильно введена фамилия автора\n");
        }
        if (publisherName == null || publisherName.isBlank() || publisherName.length() < 3 || publisherName.length() > 256) {
            errorsMessage.append("Не правильно введено название издателя\n");
        }
        if (publisherCity == null || publisherCity.isBlank() || publisherCity.length() < 3 || publisherCity.length() > 256) {
            errorsMessage.append("Не правильно введен город издателя\n");
        }
        if (kind == null || kind.isBlank() || kind.length() < 3 || kind.length() > 256) {
            errorsMessage.append("Не правильно введен жанр книги\n");
        }
        if (year == null || year.isBlank() || !year.matches(yearRegex)) {
            errorsMessage.append("Не правильно введен год издания\n");
        }

        if (errorsMessage.length() > 0) {
            throw new BookValidationException(errorsMessage.toString());
        }
    }

    public static String getErrorMessageFromBookFields(String title, String authorName, String authorSurname, String publisherName, String publisherCity, String kind, String yearPub) {
        StringBuilder errorMessage = new StringBuilder();

        if (title == null || title.length() == 0) errorMessage.append("Не обнаружено название книги\n");
        if (authorName == null || authorName.length() == 0) errorMessage.append("Не обнаружено имя автора\n");
        if (authorSurname == null || authorSurname.length() == 0) errorMessage.append("Не обнаружена фамилия автора\n");
        if (publisherName == null || publisherName.length() == 0)
            errorMessage.append("Не обнаружено название издательства\n");
        if (publisherCity == null || publisherCity.length() == 0)
            errorMessage.append("Не обнаружен город издательства\n");
        if (kind == null || kind.length() == 0) errorMessage.append("Не обнаружен жанр книги\n");
        if (yearPub == null || yearPub.length() == 0) errorMessage.append("Не обнаружен год издания\n");
        else {
            try {
                Integer.parseInt(yearPub);
            } catch (NumberFormatException e) {
                errorMessage.append("Не корректное значение года выпуска книги (должно быть целочисленным)\n");
            }
        }
        if (kind == null || kind.length() == 0) errorMessage.append("Не обнаружен жанр книги\n");

        return errorMessage.toString();
    }
}
