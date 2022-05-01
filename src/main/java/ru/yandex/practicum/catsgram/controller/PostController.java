package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findALl(
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page) {

        if (page < 0 || size < 0) {
            throw new IllegalStateException();
        }
        return postService.findAll(sort, size, page);
    }

    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable int postId) {
        return postService.getPost(postId);
    }

    @PostMapping(value = "/post")
    public void create(@RequestBody Post post) {
        postService.create(post);
    }
}