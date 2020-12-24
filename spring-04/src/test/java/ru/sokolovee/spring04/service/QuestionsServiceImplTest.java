package ru.sokolovee.spring04.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sokolovee.spring04.config.ReaderConfig;
import ru.sokolovee.spring04.dto.QuestionDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса QuestionsService")
@SpringBootTest
class QuestionsServiceImplTest {

    @Autowired
    private ReaderConfig reader;

    @DisplayName("Возвращает правильный список")
    @Test
    void testQuestionsList() {
        QuestionsServiceImpl questionService = new QuestionsServiceImpl(reader);
        List<QuestionDto> list = questionService.getQuestions();

        assertEquals(5, list.size());
        assertEquals("Is the orange round?", list.get(0).getQuestion());
        assertEquals("yes", list.get(0).getAnswer());
    }
}