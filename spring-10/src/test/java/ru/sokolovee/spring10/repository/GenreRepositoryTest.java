package ru.sokolovee.spring10.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring10.entities.Genre;
import ru.sokolovee.spring10.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами должно")
@DataJpaTest
@Transactional
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genre;

    @DisplayName("возвращать жанр по его id")
    @Test
    void shouldGetGenre() {
        var expectedGenre = new Genre(1l, "Фантастика");
        var actualGenre = genre.findById(1l).orElse(null);
        assertThat(actualGenre)
                .isEqualTo(expectedGenre);
    }
}