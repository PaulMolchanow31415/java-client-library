package edu.client.response;

import edu.client.entity.Book;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookResponse extends BaseResponse {
    private Book data;

    public BookResponse(boolean success, String message, Book data) {
        super(success, message);
        this.data = data;
    }
}
