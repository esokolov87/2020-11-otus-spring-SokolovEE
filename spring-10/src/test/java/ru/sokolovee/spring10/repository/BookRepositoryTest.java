package ru.sokolovee.spring10.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring10.entities.Author;
import ru.sokolovee.spring10.entities.Book;
import ru.sokolovee.spring10.entities.Genre;
import ru.sokolovee.spring10.repositories.BookRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repository для работы с книгами должно")
@DataJpaTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepositoryJpa;
    @Autowired
    private EntityManager em;

    @DisplayName("возвращать книгу по его id")
    @Test
    void shouldGetBook() {
        var actualBook = bookRepositoryJpa.findById(1l).orElse(null);
        var expectedBook = em.find(Book.class, 1l);
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
        var actualBooks = bookRepositoryJpa.findAll();
        assertThat(actualBooks)
                .isNotNull()
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(5l, "Новая книга", new Author(1l, "Пушкин А.С."), new Genre(1l, "Фантастика"));
        bookRepositoryJpa.save(expectedBook);
        em.detach(expectedBook);
        var actualBook = bookRepositoryJpa.findById(5l).orElse(null);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("менять данные книги в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = bookRepositoryJpa.findById(2l).orElse(null);
        expectedBook.setName("new Name");
        expectedBook.setAuthor(new Author(2, "Стивен Кинг"));
        bookRepositoryJpa.save(expectedBook);
        var actualBook = bookRepositoryJpa.findById(2l).orElse(null);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу в БД по id")
    @Test
    void shouldDeleteBook() {
        bookRepositoryJpa.deleteById(2l);
        assertThat(bookRepositoryJpa.findById(2l))
                .isEqualTo(Optional.empty());
    }

}