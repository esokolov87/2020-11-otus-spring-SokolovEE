package ru.sokolovee.spring08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.sokolovee.spring08.documents.Genre;
import ru.sokolovee.spring08.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repository для работы с жанрами должно")
@DataMongoTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genre;

    @DisplayName("возвращать жанр по его id")
    @Test
    void shouldGetGenre() {
        var expectedGenre = new Genre(1, "Фантастика");
        var actualGenre = genre.findById(1l).orElse(null);
        assertThat(actualGenre)
                .isEqualTo(expectedGenre);
    }
}