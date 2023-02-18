package edu.client.utils;

import edu.client.entity.BookEntity;

public class ValidationUtils {
    public static boolean validateBook(BookEntity book) {
        StringBuilder errorsMessage = new StringBuilder();
        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        String kind = book.getKind();
        String year = book.getYearPub();

        if (title == null || title.isBlank() || title.length() < 3 || title.length() > 255) {
            errorsMessage.append("Не правильно введено заглавие\n");
        }
        if (author == null || author.isBlank() || !author.matches("\\w{2,64}\\s\\w{2,64}")) {
            errorsMessage.append("Не правильно введено название автора\n");
        }
        if (publisher == null || publisher.isBlank() || publisher.length() < 3 || publisher.length() > 255) {
            errorsMessage.append("Не правильно введен издатель\n");
        }
        if (kind == null || kind.isBlank() || kind.length() < 3 || kind.length() > 255) {
            errorsMessage.append("Не правильно введен жанр книги\n");
        }
        if (year == null || year.isBlank() || !year.matches("^\\d{4}$")) {
            errorsMessage.append("Не правильно введен год издания\n");
        }

        return errorsMessage.length() == 0;
    }

    public static String getErrorMessageFromBookFields(String title, String author, String publisher, String kind, String yearPub) {
        StringBuilder errorMessage = new StringBuilder();

        if (title == null || title.length() == 0) errorMessage.append("Не обнаружено название книги\n");
        if (author == null || author.length() == 0) errorMessage.append("Не обнаружен автор книги\n");
        if (publisher == null || publisher.length() == 0) errorMessage.append("Не обнаружено издание книги\n");
        if (kind == null || kind.length() == 0) errorMessage.append("Не обнаружено издание книги\n");
        if (yearPub == null || yearPub.length() == 0) errorMessage.append("Не обнаружен год издания книги\n");
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
