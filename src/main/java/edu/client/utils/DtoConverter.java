package edu.client.utils;

import edu.client.model.Author;
import edu.client.model.Book;
import edu.client.model.Publisher;
import org.apache.commons.lang3.SerializationUtils;

public class DtoConverter {
    public static Book getDtoInstance(Book book) {
        Book serialized = SerializationUtils.clone(book);
        Publisher publisher = new Publisher();
        Author author = new Author();

        publisher.setId(serialized.getPublisher().getId());
        author.setId(serialized.getAuthor().getId());
        serialized.setAuthor(author);
        serialized.setPublisher(publisher);
        return serialized;
    }
}
