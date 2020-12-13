package ru.sokolovee.homework;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sokolovee.homework.dao.QuestionDto;
import ru.sokolovee.homework.dao.StudentDto;
import ru.sokolovee.homework.service.QuestionsService;
import ru.sokolovee.homework.service.TestService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        TestService testService = context.getBean(TestService.class);
        testService.startTest();

    }
}

