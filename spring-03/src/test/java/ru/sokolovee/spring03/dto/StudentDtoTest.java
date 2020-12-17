package ru.sokolovee.spring03.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentDtoTest {

    @Test
    void testGetterSetter() {
        StudentDto s = new StudentDto();
        s.setName("Иван");
        s.setSurname("Иванов");
        s.setRate((byte)25);

        assertEquals("Иван", s.getName());
        assertEquals("Иванов", s.getSurname());
        assertEquals((byte)25, s.getRate());
    }
}