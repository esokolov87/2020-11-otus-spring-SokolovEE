package ru.sokolovee.spring04.service;

import ru.sokolovee.spring04.dto.QuestionDto;

import java.util.List;

public interface QuestionsService {
    List<QuestionDto> getQuestions();
}
