package edu.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookEntity {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String yearPub;
    private String kind;
}
