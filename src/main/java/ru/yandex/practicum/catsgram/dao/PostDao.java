package ru.yandex.practicum.catsgram.dao;

import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;

public interface PostDao {
    Collection<Post> findPostsByUser(User user);
}
