package edu.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Publisher {
    private Integer id;
    private String name;
    private String city;

    @Override
    public Publisher clone() {
        try {
            return (Publisher) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }

    public static Publisher getNullObject() {
        return Publisher.builder()
                .id(null)
                .name("")
                .city("")
                .build();
    }

    /*
    public static Publisher getNullObject() {
        return Publisher.builder()
                .id(null)
                .name("empty name publisher")
                .city("empty city publisher")
                .build();
    }
    */
}
