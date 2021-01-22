package ru.sokolovee.spring06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring06.entities.Author;
import ru.sokolovee.spring06.repositories.AuthorRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@Import(AuthorRepositoryJpaImpl.class)
@DataJpaTest
@Transactional
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpaImpl author;

    @DisplayName("возвращать автора по его id")
    @Test
    void shouldGetAuhtor() {
        assertThat(author.getById(1l))
                .isEqualTo(new Author(1, "Пушкин А.С."));
    }
}