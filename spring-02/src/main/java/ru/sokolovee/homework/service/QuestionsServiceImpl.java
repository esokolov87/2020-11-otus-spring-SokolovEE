package ru.sokolovee.homework.service;

import ru.sokolovee.homework.dao.QuestionDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionsServiceImpl implements QuestionsService {

    private final String fileName;

    public QuestionsServiceImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<QuestionDto> getQuestions() {
        List<QuestionDto> questions = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName))));
            String line = br.readLine();
            while (line != null) {
                String[] qa = line.split(";");
                questions.add(new QuestionDto(qa[0], qa[1]));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
