package ru.sokolovee.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.sokolovee.spring05.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Dao для работы с авторами должно")
@Import(AuthorDaoJdbc.class)
@JdbcTest
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("возвращать автора по его id")
    @Test
    void shouldGetAuhtor() {
        assertThat(authorDao.getById(1l))
                .isEqualTo(new Author(1, "Пушкин А.С."));
    }
}