package ru.yandex.practicum.catsgram.handler;

import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserHandler {
    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User createUser(User user) {
        checkEmail(user);
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Данный адрес электронной почты уже зарегистрирован. " +
                    user.getEmail());
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User updateUser(User user) {
        checkEmail(user);
        if (users.containsKey(user.getEmail())) {
            users.remove(user.getEmail());
        }
        users.put(user.getEmail(), user);
        return user;
    }

    private void checkEmail(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
    }
}
