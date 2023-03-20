package edu.client.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class JsonParser<E> {
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

    public List<E> stringToArray(String s, Class<E[]> clazz) {
        E[] arr = gson.fromJson(s, clazz);
        return Arrays.asList(arr);
        /// https://futurestud.io/tutorials/gson-advanced-custom-serialization-part-1#:~:text=Gson%20Series%20Overview
    }

    public E desirialize(String jsonString) {
        JsonObject base = gson.fromJson(jsonString, JsonObject.class);
        Type entityType = new TypeToken<E>() {}.getType();
        return gson.fromJson(base.get("data"), entityType);
    }

    public String toJsonString(E entity) {
        return gson.toJson(entity);
    }
}