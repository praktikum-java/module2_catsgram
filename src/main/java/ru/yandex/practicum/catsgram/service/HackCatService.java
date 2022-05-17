package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HackCatService {

    private Logger log = LoggerFactory.getLogger(getClass());

    public static final String JDBC_URL="jdbc:postgres://127.0.0.1:5432/meow";
    public static final String JDBC_USERNAME="octopus";
    public static final String JDBC_DRIVER="org.postgresql.Driver";

    public void tryPassword(String jdbcPassword) {
        DriverManagerDataSource dataSourceConst = new DriverManagerDataSource();
        dataSourceConst.setDriverClassName(JDBC_DRIVER);
        dataSourceConst.setUrl(JDBC_URL);
        dataSourceConst.setUsername(JDBC_USERNAME);
        dataSourceConst.setPassword(jdbcPassword);
        JdbcTemplate jdbcTemplateConst = new JdbcTemplate(dataSourceConst);
        jdbcTemplateConst.execute("SELECT 1;");
    }

    public void doHackNow(){
        List<String> catWordList = Arrays.asList("meow", "purr", "shhh", "zzz");
        for (String word : catWordList) {
            try {
                tryPassword(word);
                log.info(word);
                break;
            } catch (Exception e){
                //ignore and check next password
            }
        }
    }
}
