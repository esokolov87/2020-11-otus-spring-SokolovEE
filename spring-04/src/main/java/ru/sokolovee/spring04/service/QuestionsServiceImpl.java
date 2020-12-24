package ru.sokolovee.spring04.service;

import org.springframework.stereotype.Service;
import ru.sokolovee.spring04.config.ReaderConfig;
import ru.sokolovee.spring04.dto.QuestionDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private ReaderConfig reader;

    public QuestionsServiceImpl(ReaderConfig reader) {
        this.reader = reader;
    }

    @Override
    public List<QuestionDto> getQuestions() {
        List<QuestionDto> questions = new ArrayList<>();
        try {
            BufferedReader fileReader = reader.fileReader();
            String line = fileReader.readLine();
            while (line != null) {
                String[] qa = line.split(";");
                questions.add(new QuestionDto(qa[0], qa[1]));
                line = fileReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
