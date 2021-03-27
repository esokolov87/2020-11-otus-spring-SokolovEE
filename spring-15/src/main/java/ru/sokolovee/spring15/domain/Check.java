package ru.sokolovee.spring15.domain;

import lombok.Data;

@Data
public class Check {
    private final String name;
    private final Boolean win;
    private final Long value;
}
