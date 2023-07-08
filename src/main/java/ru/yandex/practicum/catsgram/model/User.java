package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
public class User {

    @Email
    private String email;
    private String nickname;
    private LocalDate birthdate;

}