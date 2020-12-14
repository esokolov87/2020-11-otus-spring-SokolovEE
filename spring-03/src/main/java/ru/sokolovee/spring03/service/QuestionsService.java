package ru.sokolovee.spring03.service;

import ru.sokolovee.spring03.dto.QuestionDto;

import java.util.List;

public interface QuestionsService {
    List<QuestionDto> getQuestions();
}
