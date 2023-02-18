package edu.client.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEntity implements Cloneable {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String yearPub;
    private String kind;

    @Override
    public BookEntity clone() {
        try {
            return (BookEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }

    public static BookEntity getNullObject() {
        return BookEntity.builder()
                .id(null)
                .author("empty author")
                .kind("empty kind")
                .publisher("empty publisher")
                .yearPub("empty year publishing")
                .title("empty title")
                .build();
    }
}
