package ru.yandex.practicum.catsgram.model;

import java.util.List;

public class FriendsParams {
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
