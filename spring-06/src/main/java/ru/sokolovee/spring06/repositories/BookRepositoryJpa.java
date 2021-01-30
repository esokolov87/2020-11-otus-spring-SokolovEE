package ru.sokolovee.spring06.repositories;

import ru.sokolovee.spring06.entities.Book;

import java.util.List;

public interface BookRepositoryJpa {
    Long count();
    void insert(Book book);
    void update(Book book);
    Book getById(Long id);
    List<Book> getAll();
    void deleteById(Long id);
    Book save(Book book);
}
