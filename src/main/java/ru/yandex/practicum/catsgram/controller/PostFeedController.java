package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        if(friendsParams != null){
            List<Post> result = new ArrayList<>();
            for (String friend : friendsParams.friends) {
                result.addAll(postService.findAllByUserEmail(friend, friendsParams.size, friendsParams.sort));
            }
            return result;
        } else {
            throw new RuntimeException("неверно заполнены параметры");
        }
    }

    static class FriendsParams {
        private String sort;

        private Integer size;

        private List<String> friends;

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public List<String> getFriends() {
            return friends;
        }

        public void setFriends(List<String> friends) {
            this.friends = friends;
        }
    }
}
