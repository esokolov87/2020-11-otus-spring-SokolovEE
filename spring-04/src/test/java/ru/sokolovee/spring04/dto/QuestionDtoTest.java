package ru.sokolovee.spring04.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionDtoTest {

    @Test
    void testConstructor() {
        QuestionDto q = new QuestionDto("вопрос", "ответ");

        assertEquals("вопрос", q.getQuestion());
        assertEquals("ответ", q.getAnswer());
    }
}