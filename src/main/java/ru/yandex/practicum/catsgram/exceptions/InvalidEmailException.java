package ru.yandex.practicum.catsgram.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String s) {
        super(s);
    }
}