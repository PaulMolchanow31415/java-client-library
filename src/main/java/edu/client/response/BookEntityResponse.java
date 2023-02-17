package edu.client.response;

import edu.client.entity.BookEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookEntityResponse extends BaseResponse {
    private Iterable<BookEntity> data;

    public BookEntityResponse(Iterable<BookEntity> data) {
        super(true, "Данные о книге");
        this.data = data;
    }
}
