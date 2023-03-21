package edu.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Author extends Entity {
    private String name;
    private String surname;
    private String patronymic;

    public String getInitials() {
        return surname + ' ' + name.charAt(0) + '.' + patronymic.charAt(0) + '.';
    }

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
                .name("")
                .surname("")
                .patronymic("")
                .build();
    }
}
