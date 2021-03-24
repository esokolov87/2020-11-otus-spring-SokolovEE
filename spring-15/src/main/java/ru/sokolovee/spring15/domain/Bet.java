package ru.sokolovee.spring15.domain;

import lombok.Data;

@Data
public class Bet {
    private final String name;
    private final Long value;
    private final Long rate;
}
