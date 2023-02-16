package edu.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.client.entity.BookEntity;
import edu.client.response.BaseResponse;
import edu.client.response.BookListResponse;
import okhttp3.*;
import java.io.IOException;

public class HTTPUtils {

    OkHttpClient client = new OkHttpClient();
    /*GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();*/

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String get(String url, String args) throws IOException {
        Request request = new Request.Builder().url(url + args).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
