package edu.client.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Book extends Entity implements Cloneable {
    private String title; // название
    private String origin; // Происхождение книги
    private String section; // Раздел библиотеки (специальная литература, хобби, беллетристика и так далее)
    private String yearPub; // Год издания
    private Author author;
    private Publisher publisher;

    public static Book getNullObject() {
        return Book.builder()
                .title("")
                .origin("")
                .section("")
                .yearPub("")
                .author(Author.getNullObject())
                .publisher(Publisher.getNullObject())
                .build();
    }

    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(title, book.title)
                && Objects.equals(origin, book.origin)
                && Objects.equals(section, book.section)
                && Objects.equals(yearPub, book.yearPub)
                && Objects.equals(author, book.author)
                && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, origin, section, yearPub, author, publisher);
    }
}
