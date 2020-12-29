package ru.sokolovee.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.sokolovee.spring05.domain.Author;
import ru.sokolovee.spring05.domain.Book;
import ru.sokolovee.spring05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Dao для работы с книгами должно")
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@JdbcTest
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @DisplayName("возвращать книгу по его id")
    @Test
    void shouldGetBook() {
        assertThat(bookDao.getById(1l))
                .isEqualTo(new Book(1, "Руслан и Людмила", new Author(1, "Пушкин А.С."), new Genre(4, "Классика")));
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldGetAllBooks() {
        var expectedBooks = List.of(
                new Book(1, "Руслан и Людмила", new Author(1, "Пушкин А.С."), new Genre(4, "Классика")),
                new Book(2, "Игра престолов", new Author(3, "Джорж Мартин"), new Genre(1, "Фантастика")),
                new Book(3, "Краткая история времени", new Author(4, "Стивен Хокинг"), new Genre(2, "Научная литература")),
                new Book(4, "Оно", new Author(2, "Стивен Кинг"), new Genre(3, "Ужасы")));
        var actualBooks = bookDao.getAll();
        assertThat(actualBooks).containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        bookDao.insert(5l, "Новая книга", 1l, 1l);
        assertThat(bookDao.getById(5l))
                .isEqualTo(new Book(5, "Новая книга", new Author(1, "Пушкин А.С."), new Genre(1, "Фантастика")));
    }

    @DisplayName("возвращать колличесво книг в БД")
    @Test
    void shouldGetCountBooks() {
        assertThat(bookDao.count())
                .isEqualTo(4);
    }

    @DisplayName("менять данные книги в БД")
    @Test
    void shouldUpdateBook() {
        bookDao.update(2l, "Новая игра престолов", 2l, 3l);
        assertThat(bookDao.getById(2l))
                .isEqualTo(new Book(2, "Новая игра престолов", new Author(2, "Стивен Кинг"), new Genre(3, "Ужасы")));
    }

    @DisplayName("удалять книгу в БД по id")
    @Test
    void shouldDeleteBook() {
        bookDao.deleteById(2l);
        assertThat(bookDao.count())
                .isEqualTo(3);
        assertThatThrownBy(() -> bookDao.getById(2l))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}