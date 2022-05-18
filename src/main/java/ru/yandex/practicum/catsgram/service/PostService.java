package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;

    public PostService(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() ->new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден."));

        return postDao.findPostsByUser(user);
    }

    public Collection<Post> findPostsByUser(String authorId, Integer size, String sort) {
        return findPostsByUser(authorId)
                .stream()
                .sorted((p0, p1) -> {
                    int comp = p0.getCreationDate().compareTo(p1.getCreationDate()); //прямой порядок сортировки
                    if (sort.equals("desc")) {
                        comp = -1 * comp; //обратный порядок сортировки
                    }
                    return comp;
                })
                .limit(size)
                .collect(Collectors.toList());
    }
}
