package edu.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@Builder
@RequiredArgsConstructor
public class Publisher extends Entity implements Cloneable {
    private String name;
    private String city;

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

    @Override
    public Publisher clone() {
        try {
            return (Publisher) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher publisher)) return false;
        return Objects.equals(name, publisher.name)
                && Objects.equals(city, publisher.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}
