package edu.client.response;

import edu.client.entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class BookListResponse extends BaseResponse {
    private List<Book> data;

    public BookListResponse(List<Book> data) {
        super(true, "Список книг");
        this.data = data;
    }

    public BookListResponse() {
        super(true, "Данные о книге");
    }
}
