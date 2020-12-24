package ru.sokolovee.spring04.dto;

public class StudentDto {
    private String name;
    private String surname;
    private Integer rate;

    public StudentDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
