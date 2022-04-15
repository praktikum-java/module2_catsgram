package ru.yandex.practicum.catsgram.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.models.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final HashSet<User> users = new HashSet<>();

    @GetMapping
    public Set<User> getAll() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users;
    }

    @PostMapping
    public void create(@RequestBody User user) throws UserAlreadyExistException {
        if (user.getEmail() == null) {
            throw new InvalidEmailException("Invalid Email");
        }
        if (users.contains(user)) {
            throw new UserAlreadyExistException("User already exists");
        }
        log.debug("Переданный объект: {}",user);
        users.add(user);
    }

    @PutMapping
    public void putUser(@RequestBody User user) {
        if (user.getEmail() == null) {
            throw new InvalidEmailException("Invalid Email");
        }
        if (users.contains(user)) {
            users.remove(user);
        }
        log.debug("Переданный объект: {}",user.toString());
        users.add(user);
    }

}
