package ru.sokolovee.spring04.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.sokolovee.spring04.config.AppProps;
import ru.sokolovee.spring04.config.ReaderConfig;
import ru.sokolovee.spring04.dto.QuestionDto;
import ru.sokolovee.spring04.dto.StudentDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class TestServiceImpl implements TestService {

    private final QuestionsServiceImpl questionService;
    private final MessageSource messageSource;
    private final AppProps props;
    private final ReaderConfig reader;

    public TestServiceImpl(QuestionsServiceImpl questionService, MessageSource messageSource, AppProps props, ReaderConfig reader) {
        this.questionService = questionService;
        this.messageSource = messageSource;
        this.props = props;
        this.reader = reader;
    }

    @Override
    public String startTest(StudentDto student) {

        List<QuestionDto> list = questionService.getQuestions();
        BufferedReader br = reader.consoleReader();
        Locale locale = props.getLocale();
        Integer count = 0;
        try {
            System.out.println(messageSource.getMessage("test.begin", null, locale));
            for (QuestionDto q : list) {
                System.out.print(q.getQuestion() + messageSource.getMessage("test.answer", null, locale));
                if (q.getAnswer().equals(br.readLine())) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageSource.getMessage("test.result", new String[]{count.toString(), String.valueOf(list.size())}, locale) + (count > list.size() / 2 ? messageSource.getMessage("test.offset", null, locale) : messageSource.getMessage("test.fail", null, locale));

    }
}
