package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.User;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private List<User> users = new ArrayList<>();

    @GetMapping()
    public List<User> findAll() {
        if (users.size() == 0) {
            System.out.println("Список пользователей пока пустой");
        }
        return users;
    }

    @PostMapping()
    public User createUser(@RequestBody User user) throws UserAlreadyExistException {
        if (users.contains(user.getEmail())) {
            throw new UserAlreadyExistException
                    ("Пользователь с указанным адресом электронной почты уже был добавлен ранее");
        }else {
            users.add(user);
            return user;
        }
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) throws InvalidEmailException {
        if (user.getEmail() == null) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты");
        } else {
            users.add(user);
            return user;
        }
    }
}

