package ru.sokolovee.homework.service;

import ru.sokolovee.homework.dao.QuestionDto;
import ru.sokolovee.homework.dao.StudentDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestServiceImpl implements TestService {

    QuestionsServiceImpl questionService;

    public TestServiceImpl(QuestionsServiceImpl questionService) {
        this.questionService = questionService;
    }

    @Override
    public void startTest() {

        List<QuestionDto> list = questionService.getQuestions();
        StudentDto student = new StudentDto();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Byte count = 0;
        try {
            System.out.print("Ваше имя: ");
            student.setName(br.readLine());

            System.out.print("Ваше фамилия: ");
            student.setSurname(br.readLine());

            System.out.println("Начало теста");
            for (QuestionDto q : list) {
                System.out.print(q.getQuestion() + " ответ: ");
                if (q.getAnswer().equals(br.readLine())) {
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Результат тестирования: " + count + " из " + list.size() + ". " + (count > list.size() / 2 ? "Зачет" : "Незачет"));

    }
}
