package ru.sokolovee.spring04.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.sokolovee.spring04.config.AppProps;
import ru.sokolovee.spring04.dto.StudentDto;
import ru.sokolovee.spring04.service.TestServiceImpl;

import java.util.Collections;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class ShellComands {

    private final MessageSource messageSource;
    private final AppProps props;
    private final TestServiceImpl testService;

    private String name;
    private String surname;

    @ShellMethod(value = "name command", key = {"name"})
    public String setName(@ShellOption(defaultValue = "Ivan") String name) {
        Locale locale = props.getLocale();
        this.name = name;
        return messageSource.getMessage("user.name", null, locale)+name;
    }

    @ShellMethod(value = "surname command", key = {"surname"})
    public String setSurname(@ShellOption(defaultValue = "Ivanov") String surname) {
        Locale locale = props.getLocale();
        this.surname = surname;
        return messageSource.getMessage("user.surname", null, locale)+surname;
    }

    private Availability isStartTestAvailable() {
        Locale locale = props.getLocale();
        return name == null || surname == null ? Availability.unavailable((messageSource.getMessage("check.needLogin", null, locale))) : Availability.available();
    }

    @ShellMethod(value = "Start test command", key = {"test", "start"})
    @ShellMethodAvailability(value = "isStartTestAvailable")
    public String startTest() {
        StudentDto student = new StudentDto(name, surname);
        return testService.startTest(student);
    }

}
