package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.service.HackCatService;

@RestController
public class SimpleController {


    private final HackCatService hackCatService;

    public SimpleController(HackCatService hackCatService){

        this.hackCatService = hackCatService;
    }

    @GetMapping("/do-hack")
    public void doHack(){
        hackCatService.doHackNow();
    }

    @GetMapping("/home")
    public String homePage() {
        return "Котограм";
    }
}
