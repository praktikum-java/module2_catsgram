package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.controller.PostController;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final List<Post> posts = new ArrayList<>();
    private static int postId = 1;

    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(String sort, int size, int page) {
        log.debug("Текущее количество постов {}", posts.size());

        return posts.stream()
                .sorted((o1, o2) -> {
                    int comporator = o1.getCreationDate().compareTo(o2.getCreationDate());
                    if (sort.equals("desc")) {
                        comporator *= -1;
                    }
                    return comporator;
                })
                .skip((long) size * page)
                .limit(size)
                .collect(Collectors.toList());
    }

    public void create(@RequestBody Post post) {
        userService.findUserByEmail(post.getAuthor());
        post.setPostId(postId++);
        posts.add(post);
        log.info("Создан пост {}", post.toString());
    }

    public Post getPost(int postId) {
        return posts.stream()
                .filter(s -> s.getPostId() == postId)
                .findFirst()
                .orElse(null);
    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.stream()
                .filter(s -> s.getAuthor().equals(email))
                .sorted((o1, o2) -> {
                    int comparator = o1.getCreationDate().compareTo(o2.getCreationDate());

                    if (sort.equals("desc")) {
                        comparator *= -1;
                    }
                    return comparator;
                }).limit(size).collect(Collectors.toList());
    }
}