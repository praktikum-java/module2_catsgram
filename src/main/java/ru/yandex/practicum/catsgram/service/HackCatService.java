package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HackCatService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String JDBC_URL = "jdbc:postgresql://127.0.0.1:5432/cats";
    public static final String JDBC_USERNAME = "kitty";
    public static final String JDBC_DRIVER = "org.postgresql.Driver";

    public void tryPassword(String jdbcPassword) {
        DriverManagerDataSource dataSourceConst = new DriverManagerDataSource();
        dataSourceConst.setDriverClassName(JDBC_DRIVER);
        dataSourceConst.setUrl(JDBC_URL);
        dataSourceConst.setUsername(JDBC_USERNAME);
        dataSourceConst.setPassword(jdbcPassword);
        JdbcTemplate jdbcTemplateConst = new JdbcTemplate(dataSourceConst);
        jdbcTemplateConst.execute("SELECT 1;");
    }


    public Optional<String> doHackNow() {
        List<String> catWordList = Arrays.asList("meow", "purr", "purrrrrr", "zzz");
        for (String word : catWordList) {
            try {
                tryPassword(word);
                return Optional.of(word);
            } catch (Exception e) {
                log.info("Такой пароль не подходит: " + word);
            }
        }
        return Optional.empty();
    }

}
