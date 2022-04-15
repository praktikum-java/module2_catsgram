package ru.yandex.practicum.catsgram.controllers;

public class InvalidEmailException extends IllegalArgumentException{
    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
