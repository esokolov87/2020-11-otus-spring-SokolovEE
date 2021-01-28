package ru.sokolovee.spring08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.sokolovee.spring08.documents.Author;
import ru.sokolovee.spring08.documents.Book;
import ru.sokolovee.spring08.documents.Genre;
import ru.sokolovee.spring08.repositories.BookRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами должно")
@DataMongoTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @DisplayName("возвращать книгу по его id")
    @Test
    void shouldGetBook() {
        var actualBook = bookRepository.findById(1l).orElse(null);
        var expectedBook = mongoTemplate.findById(1l, Book.class);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldGetAllBooks() {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(1l, 2l, 3l, 4l));
        var expectedBooks = mongoTemplate.find(query, Book.class);
        var actualBooks = bookRepository.findAll();
        assertThat(actualBooks)
                .isNotNull()
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(expectedBooks);
    }

    @DisplayName("возвращать колличесво книг в БД")
    @Test
    void shouldGetCountBooks() {
        assertThat(bookRepository.count())
                .isEqualTo(4);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(5l, "Новая книга", new Author(1l, "Пушкин А.С."), new Genre(1l, "Фантастика"));
        bookRepository.save(expectedBook);
        var actualBook = bookRepository.findById(5l).orElse(null);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("менять данные книги в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = bookRepository.findById(2l).orElse(null);
        expectedBook.setName("new Name");
        expectedBook.setAuthor(new Author(2, "Стивен Кинг"));
        bookRepository.save(expectedBook);
        var actualBook = bookRepository.findById(2l).orElse(null);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу в БД по id")
    @Test
    void shouldDeleteBook() {
        bookRepository.deleteById(2l);
        assertThat(bookRepository.count())
                .isEqualTo(3);
        assertThat(bookRepository.findById(2l))
                .isEqualTo(Optional.empty());
    }

}