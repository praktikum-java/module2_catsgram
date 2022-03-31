package ru.yandex.practicum.catsgram.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.models.Post;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private final List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post> findAll() {
        return posts;
    }

    @PostMapping(value = "/post")
    public void create(@RequestBody Post post) {
        posts.add(post);
    }
}