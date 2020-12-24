package ru.sokolovee.spring05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private final Author author;
    private final Genre genre;
}
