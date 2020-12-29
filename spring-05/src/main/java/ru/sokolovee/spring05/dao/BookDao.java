package ru.sokolovee.spring05.dao;

import ru.sokolovee.spring05.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(long id, String name, long authorId, long genreId);
    void update(long id, String name, long authorId, long genreId);
    Book getById(long id);
    List<Book> getAll();
    void deleteById(long id);
}
