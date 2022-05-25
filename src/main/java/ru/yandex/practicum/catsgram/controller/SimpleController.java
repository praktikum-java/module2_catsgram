package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.service.HackCatService;

import java.util.Optional;

@RestController
public class SimpleController {


    private final HackCatService hackCatService;

    public SimpleController(HackCatService hackCatService){

        this.hackCatService = hackCatService;
    }

    @GetMapping("/do-hack")
    public Optional<String> doHack(){
                return hackCatService.doHackNow()
                        .map(password -> "Ура! Пароль подобран: " + password)
                        .or(() -> Optional.of("Не удалось подобрать пароль. "
                                + " Проверьте состояние и настройки базы данных."));
    }

    @GetMapping("/home")
    public String homePage() {
        return "Котограм";
    }
}
