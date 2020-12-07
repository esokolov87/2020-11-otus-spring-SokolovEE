package ru.sokolovee.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sokolovee.homework.service.QuestionService;

@Configuration
public class QuestionsConfig {

    @Bean
    public QuestionService questionService(){
        return new QuestionService("questions.csv");
    }

}
