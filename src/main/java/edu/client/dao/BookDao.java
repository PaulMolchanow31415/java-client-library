package edu.client.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.client.entity.BookEntity;
import edu.client.response.BaseResponse;
import edu.client.utils.HTTPUtils;

import java.io.IOException;

import static edu.client.controller.AppController.API_PATH;

public class BookDao {
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();
    public static BookEntity sendBookAndGetData(BookEntity book) throws IOException {
        String response = http.post(API_PATH + "add", gson.toJson(book));
        JsonObject base = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(base.get("data"), BookEntity.class);
    }

    public static BaseResponse deleteBook(BookEntity book) throws IOException {
        return http.delete(API_PATH, book.getId());
    }

    public static String updateBook(BookEntity book) throws IOException {
        return http.put(API_PATH + "update", gson.toJson(book));
    }

    public static String getBookData() throws IOException {
        return http.get(API_PATH, "all");
    }
}
