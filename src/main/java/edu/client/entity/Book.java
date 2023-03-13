package edu.client.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book implements Cloneable {
    private Integer id;
    private String title; // название
    private String origin; // Происхождение книги
    private String section; // Раздел библиотеки (специальная литература, хобби, беллетристика и так далее)
    private String yearPub; // Год издания
    private Author author;
    private Publisher publisher;

    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }

    public static Book getNullObject() {
        return Book.builder()
                .id(null)
                .title("empty title")
                .origin("empty origin")
                .section("empty section")
                .yearPub("empty year publishing")
                .author(Author.getNullObject())
                .publisher(Publisher.getNullObject())
                .build();
    }
}
