package ru.sokolovee.spring05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Genre {
    private final long id;
    private final String name;
}
