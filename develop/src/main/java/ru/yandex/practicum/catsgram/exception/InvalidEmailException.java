package ru.yandex.practicum.catsgram.exception;

public class InvalidEmailException extends Throwable {
    public InvalidEmailException(String message) {
        super(message);
    }
}
