package ru.sokolovee.spring08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.sokolovee.spring08.documents.Author;
import ru.sokolovee.spring08.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами должно")
@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository author;

    @DisplayName("возвращать автора по его id")
    @Test
    void shouldGetAuhtor() {
        var expectedAuthor = new Author(1, "Пушкин А.С.");
        var actualAuthor = author.findById(1l).orElse(null);
        assertThat(actualAuthor)
                .isEqualTo(expectedAuthor);
    }
}