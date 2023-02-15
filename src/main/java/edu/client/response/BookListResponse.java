package edu.client.response;

import edu.client.entity.BookEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class BookListResponse extends BaseResponse {
    private List<BookEntity> data;

    public BookListResponse(List<BookEntity> data) {
        super(true, "Список книг");
        this.data = data;
    }
}
