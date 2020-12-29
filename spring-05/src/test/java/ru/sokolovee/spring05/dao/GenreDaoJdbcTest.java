package ru.sokolovee.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.sokolovee.spring05.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Dao для работы с жанрами должно")
@Import(GenreDaoJdbc.class)
@JdbcTest
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @DisplayName("возвращать жанр по его id")
    @Test
    void shouldGetGenre() {
        assertThat(genreDao.getById(1l))
                .isEqualTo(new Genre(1, "Фантастика"));
    }
}