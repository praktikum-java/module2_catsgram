package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.exceptions.UserNotExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Map<String, User> users = new HashMap<>();
    private static int userId = 1;

    public Collection<User> findAll() {
        log.info("Количество пользователей на текущий момент {}", users.size());
        return users.values();
    }

    public User findUserById(int userId) {
        for (String email : users.keySet()) {
            if (users.get(email).getUserId() == userId) {
                return users.get(email);
            }
        } return null;
    }

    public User create(User user) {
        if (user.getEmail() == (null) || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с email " + user.getEmail() +
                    " уже существует.");
        }
        user.setUserId(userId++);
        users.put(user.getEmail(), user);
        log.info("Создан пользователь {}", user.toString());
        return user;
    }

    public User change(User user) {
        if (user.getEmail() == (null) || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
        users.put(user.getEmail(), user);

        return user;
    }

    public String findUserByEmail(String email) {
        if (!users.containsKey(email)) {
            throw new UserNotExistException("Пользователя с email " + email + " не существует.");
        }
        return null;
    }
}
