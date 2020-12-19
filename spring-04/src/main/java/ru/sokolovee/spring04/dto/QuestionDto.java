package ru.sokolovee.spring04.dto;

public class QuestionDto {
    private String question;
    private String answer;

    public QuestionDto(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

}
