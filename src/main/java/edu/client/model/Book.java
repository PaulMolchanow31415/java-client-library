package edu.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Book implements Serializable, Entity {
    private Integer id;
    private String title;
    private String origin;
    private String section;
    private String yearPub;
    private Author author;
    private Publisher publisher;

    public Book getNullObject() {
        if (author == null) author = new Author();
        if (publisher == null) publisher = new Publisher();

        return Book.builder()
                .id(this.id)
                .title("")
                .origin("")
                .section("")
                .yearPub("")
                .author(this.author.getNullObject())
                .publisher(this.publisher.getNullObject())
                .build();
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
