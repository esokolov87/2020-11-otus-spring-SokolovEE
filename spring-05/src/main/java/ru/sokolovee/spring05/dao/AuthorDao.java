package ru.sokolovee.spring05.dao;

import ru.sokolovee.spring05.domain.Author;

public interface AuthorDao {
    Author getById(long id);
}
