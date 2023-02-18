package edu.client.response;

import edu.client.entity.BookEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookEntityResponse extends BaseResponse {
    private BookEntity data;

    public BookEntityResponse(boolean success, String message, BookEntity data) {
        super(success, message);
        this.data = data;
    }
}
