package ru.sokolovee.spring06.repositories;

import ru.sokolovee.spring06.entities.Author;

public interface AuthorRepositoryJpa {
    Author getById(long id);
}
