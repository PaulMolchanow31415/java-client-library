package edu.client.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.client.entity.Book;
import edu.client.properties.AppProperties;
import edu.client.utils.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

public class BookDao implements Dao<Book> {
    public static final String API_PATH = AppProperties.getInstance().getProperty("api_path");
    private static final HttpClientUtils client = new HttpClientUtils();
    private static final Gson gson = new Gson();

    @Override
    public List<Book> getAll() throws Exception {
        List<Book> books = new ArrayList<>();
        String response = client.get(API_PATH, "all");
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (JsonElement element : dataArr) {
            Book newBook = gson.fromJson(element.toString(), Book.class);
            if (newBook != null) books.add(newBook);
        }
        return books;
    }

    /**
     * @return id of added book
     */
    @Override
    public Integer save(Book book) throws Exception {
        String response = client.post(API_PATH + "add", gson.toJson(book));
        JsonObject base = gson.fromJson(response, JsonObject.class);
        return gson.fromJson(base.get("data"), Book.class).getId();
    }

    @Override
    public void update(Book book) throws Exception {
        client.put(API_PATH + "update", gson.toJson(book));
    }

    @Override
    public void delete(Book book) throws Exception {
        client.delete(API_PATH, book.getId());
    }
}
