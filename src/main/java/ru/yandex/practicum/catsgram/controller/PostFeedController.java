package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.FriendsParams;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class PostFeedController {

    private final PostService postService;

    public PostFeedController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/feed/friends")
    List<Post> getFriendsFeed(@RequestBody String params){
        ObjectMapper objectMapper = new ObjectMapper();
        FriendsParams friendsParams;
        try {
            String paramsFromString = objectMapper.readValue(params, String.class);
            friendsParams = objectMapper.readValue(paramsFromString, FriendsParams.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("невалидный формат json", e);
        }

        if (friendsParams != null) {
            List<Post> result = new ArrayList<>();
            for (String friend : friendsParams.getFriends()) {
                result.addAll(postService.findAllByUserEmail(friend, friendsParams.getSize(), friendsParams.getSort()));
            }
            return result;
        } else {
            throw new RuntimeException("неверно заполнены параметры");
        }
    }
}
