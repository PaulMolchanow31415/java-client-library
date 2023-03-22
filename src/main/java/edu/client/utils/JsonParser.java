package edu.client.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.client.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class JsonParser<E extends Entity> {
    public static final Gson gson = new Gson();

    public List<E> serializeToList(String jsonString, Class<E> eClass) {
        List<E> entities = new ArrayList<>();
        JsonObject base = gson.fromJson(jsonString, JsonObject.class);
        JsonArray dataArray = base.getAsJsonArray("data");

        for (JsonElement element : dataArray) {
            E newEntity = gson.fromJson(element.toString(), eClass);
            if (newEntity != null) entities.add(newEntity);
        }
        return entities;
    }

    public E desirialize(String jsonString, Class<E> eClass) {
        JsonObject base = gson.fromJson(jsonString, JsonObject.class);

        System.out.println(base);

        return gson.fromJson(base.get("data"), eClass);
    }

    public String serialize(E entity, Class<E> eClass) {
        return gson.toJson(entity, eClass);
    }

}