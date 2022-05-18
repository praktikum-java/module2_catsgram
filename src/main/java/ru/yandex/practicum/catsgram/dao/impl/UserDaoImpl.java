package ru.yandex.practicum.catsgram.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Optional<User> findUserById(String id) {
        // выполняем запрос к базе данных.
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from cat_user where id = ?", id);

        // обрабатываем результат выполнения запроса
        if(userRows.next()) {
            User user = new User(
                    userRows.getString("id"),
                    userRows.getString("username"),
                    userRows.getString("nickname"));

            log.info("Найден пользователь: {} {}", user.getId(), user.getNickname());

            return Optional.of(user);
        } else {
            log.info("Пользователь с идентификатором {} не найден.", id);
            return Optional.empty();
        }
    }
}