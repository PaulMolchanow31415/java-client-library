package edu.client.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.client.entity.Book;
import edu.client.response.BaseResponse;
import edu.client.utils.HTTPUtils;

import java.io.IOException;

import static edu.client.controller.LauncherController.API_PATH;

public class BookDao {
    static HTTPUtils http = new HTTPUtils();
    static Gson gson = new Gson();
    public static Book sendBookAndGetData(Book book) throws IOException {
        String response = http.post(API_PATH + "add", gson.toJson(book));
        JsonObject base = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(base.get("data"), Book.class);
    }

    public static BaseResponse deleteBook(Book book) throws IOException {
        return http.delete(API_PATH, book.getId());
    }

    public static String updateBook(Book book) throws IOException {
        return http.put(API_PATH + "update", gson.toJson(book));
    }

    public static String getBookData() throws IOException {
        return http.get(API_PATH, "all");
    }
}
