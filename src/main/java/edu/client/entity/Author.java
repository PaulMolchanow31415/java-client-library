package edu.client.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorEntity {
    private Long id;
    private String name;
    private String surname;

    @Override
    public AuthorEntity clone() {
        try {
            return (AuthorEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return getNullObject();
    }

    public static AuthorEntity getNullObject() {
        return AuthorEntity.builder()
                .id(null)
                .name("empty name author")
                .surname("empty surname author")
                .build();
    }
}
