package edu.client.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublisherEntity {
    private Long id;
    private String name;
    private String city;

    @Override
    public PublisherEntity clone() {
        try {
            return (PublisherEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }

    public static PublisherEntity getNullObject() {
        return PublisherEntity.builder()
                .id(null)
                .name("empty name publisher")
                .city("empty city publisher")
                .build();
    }
}
