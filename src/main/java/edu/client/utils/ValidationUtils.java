package edu.client.utils;

import edu.client.entity.Book;
import edu.client.exception.BookValidationException;

public class ValidationUtils {
    public static void validateBook(Book book) throws BookValidationException {
        StringBuilder errorsMessage = new StringBuilder();

        /* regular expressions */
        String authorRegex = "^[A-Z|А-Я][a-z|а-я]{2,32}$";
        String yearRegex = "^\\d{4}$";

        /* unique */
        String title = book.getTitle();
        String section = book.getSection();
        String origin = book.getOrigin();
        String year = book.getYearPub();

        /* author */
        String authorName = book.getAuthor().getName();
        String authorSurname = book.getAuthor().getSurname();
        String authorPatronymic = book.getAuthor().getPatronymic();

        /* publisher */
        String publisherName = book.getPublisher().getName();
        String publisherCity = book.getPublisher().getCity();

        /* unique */
        if (title == null || title.isBlank() || title.length() < 3 || title.length() > 255) {
            errorsMessage.append("Не правильно введено заглавие\n");
        }
        if (section == null || section.isBlank() || section.length() < 3 || section.length() > 255) {
            errorsMessage.append("Не правильно введена секция\n");
        }
        if (origin == null || origin.isBlank() || origin.length() < 3 || origin.length() > 255) {
            errorsMessage.append("Не правильно введено происхождение\n");
        }
        if (year == null || year.isBlank() || !year.matches(yearRegex)) {
            errorsMessage.append("Не правильно введен год издания\n");
        }
        /* author */
        if (authorName == null || authorName.isBlank() || !authorName.matches(authorRegex)) {
            errorsMessage.append("Не правильно введено имя автора\n");
        }
        if (authorSurname == null || authorSurname.isBlank() || !authorSurname.matches(authorRegex)) {
            errorsMessage.append("Не правильно введена фамилия автора\n");
        }
        if (authorPatronymic == null || authorPatronymic.isBlank() || !authorPatronymic.matches(authorRegex)) {
            errorsMessage.append("Не правильно введена фамилия автора\n");
        }
        /* publisher */
        if (publisherName == null || publisherName.isBlank() || publisherName.length() < 3 || publisherName.length() > 255) {
            errorsMessage.append("Не правильно введено название издателя\n");
        }
        if (publisherCity == null || publisherCity.isBlank() || publisherCity.length() < 3 || publisherCity.length() > 255) {
            errorsMessage.append("Не правильно введен город издателя\n");
        }

        if (errorsMessage.length() > 0) {
            throw new BookValidationException(errorsMessage.toString());
        }
    }
}
