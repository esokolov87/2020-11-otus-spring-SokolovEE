package ru.sokolovee.spring06.repositories;

import ru.sokolovee.spring06.entities.Genre;

public interface GenreRepositoryJpa {
    Genre getById(long id);
}
