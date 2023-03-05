package edu.client.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Publisher {
    private Long id;
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
                .name("empty name publisher")
                .city("empty city publisher")
                .build();
    }
}
