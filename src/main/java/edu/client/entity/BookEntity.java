package edu.client.entity;

import lombok.Data;

@Data
public class BookEntity {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String yearPub;
    private String kind;
}
