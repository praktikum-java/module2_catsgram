package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class User {

    private String email;
    private String nickname;
    private LocalDate birthdate;
    private int userId;

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
