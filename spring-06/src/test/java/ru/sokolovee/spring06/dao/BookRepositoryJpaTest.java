package ru.sokolovee.spring06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring06.entities.Author;
import ru.sokolovee.spring06.entities.Book;
import ru.sokolovee.spring06.entities.Comment;
import ru.sokolovee.spring06.entities.Genre;
import ru.sokolovee.spring06.repositories.AuthorRepositoryJpaImpl;
import ru.sokolovee.spring06.repositories.BookRepositoryJpaImpl;
import ru.sokolovee.spring06.repositories.CommentRepositoryJpaImpl;
import ru.sokolovee.spring06.repositories.GenreRepositoryJpaImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Dao для работы с книгами должно")
@Import({BookRepositoryJpaImpl.class, AuthorRepositoryJpaImpl.class, GenreRepositoryJpaImpl.class, CommentRepositoryJpaImpl.class})
@DataJpaTest
@Transactional
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;
    @Autowired
    private EntityManager em;

    @DisplayName("возвращать книгу по его id")
    @Test
    void shouldGetBook() {
        var actualBook = bookRepositoryJpa.getById(1l);
        var expectedBook = new Book(1l, "Руслан и Людмила", new Author(1l, "Пушкин А.С."), new Genre(4l, "Классика"), Stream.of(new Comment(1l, 1l, "Книга Пушкина А.С. \"Руслан и Людмила\", жанр Классика")).collect(Collectors.toList()));
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldGetAllBooks() {
        var expectedBooks = List.of(
                em.find(Book.class, 1l),
                em.find(Book.class, 2l),
                em.find(Book.class, 3l),
                em.find(Book.class, 4l)
        );
        var actualBooks = bookRepositoryJpa.getAll();
        assertThat(actualBooks)
                .isNotNull()
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(5l, "Новая книга", new Author(1l, "Пушкин А.С."), new Genre(1l, "Фантастика"), null);
        bookRepositoryJpa.save(expectedBook);
        em.detach(expectedBook);
        var actualBook = bookRepositoryJpa.getById(5l);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать колличесво книг в БД")
    @Test
    void shouldGetCountBooks() {
        assertThat(bookRepositoryJpa.count())
                .isEqualTo(4);
    }

    @DisplayName("менять данные книги в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = bookRepositoryJpa.getById(2l);
        expectedBook.setName("new Name");
        expectedBook.setAuthor(new Author(2, "Стивен Кинг"));
        bookRepositoryJpa.update(expectedBook);
        var actualBook = bookRepositoryJpa.getById(2l);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу в БД по id")
    @Test
    void shouldDeleteBook() {
        bookRepositoryJpa.deleteById(2l);
        assertThat(bookRepositoryJpa.count())
                .isEqualTo(3);
        assertThatThrownBy(() -> bookRepositoryJpa.getById(2l))
                .isInstanceOf(NoResultException.class);
    }

}