package ru.sokolovee.homework.service;

import ru.sokolovee.homework.dao.QuestionDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionService {

    private final String fileName;

    public QuestionService(String fileName) {
        this.fileName = fileName;
    }

    public List<QuestionDao> getQuestions() {
        List<QuestionDao> questions = new ArrayList<QuestionDao>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName))));
            String line = br.readLine();
            while (line != null) {
                String[] qa = line.split(";");
                questions.add(new QuestionDao(qa[0], qa[1]));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
