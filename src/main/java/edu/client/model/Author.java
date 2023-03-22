package edu.client.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Author extends Entity implements Cloneable {
    private String name;
    private String surname;
    private String patronymic;

    public static Author getNullObject() {
        return Author.builder()
                .name("")
                .surname("")
                .patronymic("")
                .build();
    }

    public String getInitials() {
        if (name != null && surname != null && patronymic != null) {
            return surname + ' ' + name.charAt(0) + '.' + patronymic.charAt(0) + '.';
        }
        return "Данные автора не заполнены";
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + patronymic;
    }

    @Override
    public Author clone() {
        try {
            return (Author) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(name, author.name)
                && Objects.equals(surname, author.surname)
                && Objects.equals(patronymic, author.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic);
    }
}
