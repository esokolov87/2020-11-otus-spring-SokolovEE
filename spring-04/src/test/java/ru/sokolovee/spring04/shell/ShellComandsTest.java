package ru.sokolovee.spring04.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест команд shell ")
@SpringBootTest
class ShellComandsTest {

    public static final String COMMAND_NAME = "name";
    public static final String COMMAND_SURNAME = "surname";
    public static final String COMMAND_START_TEST = "test";

    @Autowired
    private Shell shell;

    @DisplayName("проверка команды ввода имени")
    @Test
    void shouldSetName() {
        String res = (String) shell.evaluate(() -> COMMAND_NAME);
        assertThat(res).isEqualTo("Your name: Ivan");
    }

    @DisplayName("проверка команды ввода фамилии")
    @Test
    void shouldSetSurname() {
        String res = (String) shell.evaluate(() -> COMMAND_SURNAME);
        assertThat(res).isEqualTo("Your surname: Ivanov");
    }

    @DisplayName("проверка валидации ввода имени и фамилии")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldCheckNameAndSurname() {
        Object res = shell.evaluate(() -> COMMAND_START_TEST);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

}