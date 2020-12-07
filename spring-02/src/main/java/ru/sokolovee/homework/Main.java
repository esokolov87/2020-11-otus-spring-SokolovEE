package ru.sokolovee.homework;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sokolovee.homework.dao.QuestionDao;
import ru.sokolovee.homework.dao.StudentDao;
import ru.sokolovee.homework.service.QuestionService;

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
        QuestionService questionService = context.getBean(QuestionService.class);
        List<QuestionDao> list = questionService.getQuestions();
        StudentDao student = new StudentDao();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Byte count = 0;
        try {
            System.out.print("Ваше имя: ");
            student.setName(br.readLine());

            System.out.print("Ваше фамилия: ");
            student.setSurname(br.readLine());

            System.out.println("Начало теста");
            for (QuestionDao q : list) {
                System.out.print(q.getQuestion() + " ответ: ");
                if(q.getAnswer().equals(br.readLine())){
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Результат тестирования: "+count+" из "+list.size()+". "+(count>list.size()/2? "Зачет" : "Незачет"));


    }
}

