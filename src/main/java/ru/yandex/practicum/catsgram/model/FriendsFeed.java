package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.util.List;

@Data
public class FriendsFeed {
    private String sort;
    private int size;
    List<String> friends;
}
