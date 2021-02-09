package ru.sokolovee.spring09.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring09.entities.Author;
import ru.sokolovee.spring09.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами должно")
@DataJpaTest
@Transactional
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