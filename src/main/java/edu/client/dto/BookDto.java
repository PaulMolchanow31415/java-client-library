package edu.client.dto;

import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Entity;
import edu.client.model.Publisher;

public class BookDto extends Entity {
    private final String title;
    private final String origin;
    private final String section;
    private final String yearPub;
    private final Author author;
    private final Publisher publisher;

    public BookDto(Book book) {
        Book t = book.clone();
        this.title = t.getTitle();
        this.origin = t.getOrigin()

        Publisher tempPublisher = publisher.clone();
        Publisher out = new Publisher();
        out.setId(tempPublisher.getId());

        Author tempAuthor = author.clone();
        Author out = new Author();
        out.setId(tempAuthor.getId());

        t.setAuthor(author.toDTO());
        t.setPublisher(publisher.toDTO());
    }
}
