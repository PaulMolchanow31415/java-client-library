package edu.client.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import edu.client.model.Book;
import edu.client.model.Entity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonParser<E extends Entity> {
    public static final Gson gson = new Gson();

    /*public List<E> fromJsonList(String jsonList) {
        List<E> entities = new ArrayList<>();
        Type entityType = new TypeToken<E>() {
        }.getType();
        JsonObject base = gson.fromJson(jsonList, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");
        for (JsonElement element : dataArr) {
            E newEntity = gson.fromJson(element.toString(), entityType);
            if (newEntity != null) entities.add(newEntity);
        }
        return entities;
    }*/

    /*public List<E> fromJsonList(String jsonList) {
        List<E> entities = new ArrayList<>();
        JsonObject base = gson.fromJson(jsonList, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");

        System.out.println(jsonList);

        System.out.println(dataArr);

        Type entityType = new TypeToken<E>() {}.getType();

        System.out.println(entityType);

        for (JsonElement element : dataArr) {
            E newEntity = gson.fromJson(element.toString(), entityType);
            if (newEntity != null) entities.add(newEntity);
        }
        return entities;
    }*/

     /*E[] dataArr = gson.fromJson(s, clazz);

        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonSerializer<E> serializer = (src, typeOfSrc, context) -> {
            JsonObject jsonEntity = new JsonObject();
            jsonEntity.addProperty("id", src.getId());
            return jsonEntity;
        };
        gsonBuilder.registerTypeAdapter(clazz, serializer);

        for (JsonElement element : dataArr) {
            E newEntity = gson.fromJson(element.toString(), entityType);
            if (newEntity != null) entities.add(newEntity);
        }

        Gson customGson = gsonBuilder.create();
        String customJSON = customGson.toJson(s);

        return Arrays.asList(arr);*/
    /// https://futurestud.io/tutorials/gson-advanced-custom-serialization-part-1#:~:text=Gson%20Series%20Overview

    /*public List<Book> serializeToArray(String jsonString) {
        List<Book> books = new ArrayList<>();
        JsonObject base = gson.fromJson(jsonString, JsonObject.class);
        JsonArray dataArr = base.getAsJsonArray("data");

        for (JsonElement element : dataArr) {
            Book newBook = gson.fromJson(element.toString(), Book.class);
            if (newBook != null) books.add(newBook);
        }
        return books;
    }*/

    public List<E> serializeToArray(String jsonString, Class<E> eClass) {
        List<E> entities = new ArrayList<>();
        JsonObject base = gson.fromJson(jsonString, JsonObject.class);
        JsonArray dataArray = base.getAsJsonArray("data");

        for (JsonElement element : dataArray) {
            E newEntity = gson.fromJson(element.toString(), eClass);
            if (newEntity != null) entities.add(newEntity);
        }
        return entities;
    }

    public E desirialize(String jsonString) {
        JsonObject base = gson.fromJson(jsonString, JsonObject.class);
        Type entityType = new TypeToken<E>() {
        }.getType();

        return gson.fromJson(base.get("data"), entityType);
    }

    public String toJsonString(E entity) {
        return gson.toJson(entity);
    }
}