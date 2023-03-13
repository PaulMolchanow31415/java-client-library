package edu.client.utils;

import edu.client.properties.AppProperties;
import okhttp3.*;

public class HttpClientUtils {
    private static final String DEFAULT_MEDIA_TYPE = AppProperties.getInstance().getProperty("json_media_type");
    private static final OkHttpClient http = new OkHttpClient();

    public String get(String url, String argument) throws Exception {
        Request request = new Request.Builder().url(url + argument).build();
        try (Response response = http.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String url, String json) throws Exception {
        RequestBody body = RequestBody.create(json, MediaType.get(DEFAULT_MEDIA_TYPE));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = http.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String put(String url, String json) throws Exception {
        RequestBody body = RequestBody.create(json, MediaType.get(DEFAULT_MEDIA_TYPE));
        Request request = new Request.Builder().url(url).put(body).build();
        try (Response response = http.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String delete(String url, Integer id) throws Exception {
        Request request = new Request.Builder().url(url + "delete/" + id).delete().build();
        try (Response response = http.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
