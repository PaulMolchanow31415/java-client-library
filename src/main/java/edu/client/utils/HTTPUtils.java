package edu.client.utils;

import com.google.gson.Gson;
import edu.client.response.BaseResponse;
import okhttp3.*;

import java.io.IOException;

public class HTTPUtils {

    OkHttpClient client = new OkHttpClient();
    static Gson gson = new Gson();

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

    public BaseResponse delete(String url, Long id) throws IOException {
        Request request = new Request.Builder().url(url + "delete/" + id).delete().build();
        System.out.println(url + "delete/" + id);
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), BaseResponse.class);
        }
    }

}
