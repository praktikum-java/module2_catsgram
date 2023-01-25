package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@Service
public class FeedService {
    private final FollowDao followDao;

    public FeedService(FollowDao followDao) {
        this.followDao = followDao;
    }

    public List<Post> getFeedFor(String userId, int max) {
        return followDao.getFollowFeed(userId, max);
    }
}
