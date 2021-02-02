package ru.sokolovee.spring08;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@SpringBootApplication
@EnableMongoRepositories
public class Spring08Application {
    public static void main(String[] args) {
        SpringApplication.run(Spring08Application.class, args);
    }
}
