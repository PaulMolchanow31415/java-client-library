package edu.client.utils;

import com.google.gson.Gson;
import edu.client.response.BaseResponse;
import okhttp3.*;

import java.io.IOException;

import static edu.client.controller.LauncherController.DEFAULT_MEDIA_TYPE;

public class HTTPUtils {
    OkHttpClient client = new OkHttpClient();
    static Gson gson = new Gson();

    public String get(String url, String argument) throws IOException {
        Request request = new Request.Builder().url(url + argument).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get(DEFAULT_MEDIA_TYPE));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.get(DEFAULT_MEDIA_TYPE));
        Request request = new Request.Builder().url(url).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public BaseResponse delete(String url, Long id) throws IOException {
        /* debug info */
        System.out.println("id = " + id);
        System.out.println(url + "delete/" + id);
        /* request */
        Request request = new Request.Builder().url(url + "delete/" + id).delete().build();
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), BaseResponse.class);
        }
    }

}
