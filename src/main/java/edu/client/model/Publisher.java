package edu.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Publisher extends Entity {
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
                .name("")
                .city("")
                .build();
    }

    @Override
    public String toString() {
        return name + ' ' + city;
    }
}
