package ru.sokolovee.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sokolovee.homework.service.QuestionsServiceImpl;
import ru.sokolovee.homework.service.TestServiceImpl;

@Configuration
public class QuestionsConfig {

    @Bean
    public QuestionsServiceImpl questionService(){
        return new QuestionsServiceImpl("questions.csv");
    }

    @Bean
    public TestServiceImpl testService(){
        return new TestServiceImpl(questionService());
    }
}
