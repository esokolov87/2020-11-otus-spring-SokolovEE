package ru.sokolovee.spring05.dao;

import ru.sokolovee.spring05.domain.Genre;

public interface GenreDao {
    Genre getById(long id);
}
