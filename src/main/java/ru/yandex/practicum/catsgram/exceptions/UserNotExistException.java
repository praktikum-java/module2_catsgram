package ru.yandex.practicum.catsgram.exceptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String s) {
        super(s);
    }
}