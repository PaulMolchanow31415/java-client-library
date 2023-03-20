package edu.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Book extends Entity {
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
                .title("")
                .origin("")
                .section("")
                .yearPub("")
                .author(Author.getNullObject())
                .publisher(Publisher.getNullObject())
                .build();
    }
}
