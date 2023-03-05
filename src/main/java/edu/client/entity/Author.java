package edu.client.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;

    @Override
    public Author clone() {
        try {
            return (Author) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }

    public static Author getNullObject() {
        return Author.builder()
                .id(null)
                .name("empty name author")
                .surname("empty surname author")
                .patronymic("empty patronymic author")
                .build();
    }
}
