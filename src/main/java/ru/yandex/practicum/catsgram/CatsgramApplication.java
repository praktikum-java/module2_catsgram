package ru.yandex.practicum.catsgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CatsgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatsgramApplication.class, args);
	}

}
