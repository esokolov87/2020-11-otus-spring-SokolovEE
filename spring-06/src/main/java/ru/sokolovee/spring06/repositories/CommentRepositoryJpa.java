package ru.sokolovee.spring06.repositories;

import ru.sokolovee.spring06.entities.Comment;

public interface CommentRepositoryJpa {
    Comment getById(Long id);

    void insert(Comment comment);

    void update(Comment comment);

    void delete(Long id);

    Comment save(Comment comment);
}
