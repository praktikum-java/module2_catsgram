package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.handler.UserHandler;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserHandler userHandler = new UserHandler();

    @GetMapping
    public Collection<User> findAll() {
        return userHandler.findAll();
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userHandler.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userHandler.updateUser(user);
    }
}
