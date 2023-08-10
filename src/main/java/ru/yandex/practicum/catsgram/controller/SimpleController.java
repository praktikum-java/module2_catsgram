package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class SimpleController {
    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @GetMapping("/home")
    public String homePage() {
        // логируем факт получения запроса
        log.debug("Получен запрос GET /home.");
        return "Котограм";
    }
}
