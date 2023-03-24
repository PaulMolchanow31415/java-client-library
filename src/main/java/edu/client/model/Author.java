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
public class Author implements Serializable, Entity {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;

    public Author getNullObject() {
        return Author.builder()
                .id(this.id)
                .name("")
                .surname("")
                .patronymic("")
                .build();
    }

    public String getInitials() {
        if (name != null && !name.isEmpty() && !name.isBlank()
                && surname != null && !surname.isEmpty() && !surname.isBlank()
                && patronymic != null && !patronymic.isEmpty() && !patronymic.isBlank()) {

            return surname + ' ' + name.charAt(0) + '.' + patronymic.charAt(0) + '.';
        }
        return "Пусто";
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + patronymic;
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
