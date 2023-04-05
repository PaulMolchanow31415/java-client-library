package edu.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Publisher implements Serializable, Entity {
    private Integer id;
    private String name;
    private String city;

    public Publisher getNullObject() {
        return Publisher.builder()
                .id(this.id)
                .name("")
                .city("")
                .build();
    }

    public String getLabeling() {
        if (name != null && !name.isEmpty() && !name.isBlank()) {
            return name;
        }
        return "Пусто";
    }

    @Override
    public String toString() {
        return name + ' ' + city;
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
