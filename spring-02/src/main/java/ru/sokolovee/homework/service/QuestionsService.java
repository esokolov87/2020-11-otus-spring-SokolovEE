package ru.sokolovee.homework.service;

import ru.sokolovee.homework.dao.QuestionDto;

import java.util.List;

public interface QuestionsService {
    List<QuestionDto> getQuestions();
}
