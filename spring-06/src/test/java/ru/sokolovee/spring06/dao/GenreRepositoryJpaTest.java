package ru.sokolovee.spring06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring06.entities.Genre;
import ru.sokolovee.spring06.repositories.GenreRepositoryJpaImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@Import(GenreRepositoryJpaImpl.class)
@DataJpaTest
@Transactional
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepositoryJpaImpl genre;

    @DisplayName("возвращать жанр по его id")
    @Test
    void shouldGetGenre() {
        assertThat(genre.getById(1l))
                .isEqualTo(new Genre(1, "Фантастика"));
    }
}