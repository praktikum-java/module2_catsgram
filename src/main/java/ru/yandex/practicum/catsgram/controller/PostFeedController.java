package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.FriendsFeed;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed/friends")
public class PostFeedController {

    PostService postService;

    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public List<Post> getFriendsFeed(@RequestBody String params) {
        ObjectMapper objectMapper = new JsonMapper();
        FriendsFeed friendsFeed;

        try {
            String paramsString = objectMapper.readValue(params, String.class);
            friendsFeed = objectMapper.readValue(paramsString, FriendsFeed.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("не валидный формат json", e);
        }

        if (friendsFeed != null) {
            List<Post> result = new ArrayList<>();
            for (String email : friendsFeed.getFriends()) {
                result.addAll(postService.findAllByUserEmail(email, friendsFeed.getSize(), friendsFeed.getSort()));
            }
            return result;
        } else {
            throw new RuntimeException("неверно заполнены параметры");
        }
    }
}
