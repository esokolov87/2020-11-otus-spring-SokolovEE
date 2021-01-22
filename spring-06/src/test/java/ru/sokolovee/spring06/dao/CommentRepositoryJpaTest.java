package ru.sokolovee.spring06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring06.entities.Comment;
import ru.sokolovee.spring06.repositories.CommentRepositoryJpaImpl;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@Import(CommentRepositoryJpaImpl.class)
@DataJpaTest
@Transactional
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpaImpl commentJpa;
    @Autowired
    private EntityManager em;

    @DisplayName("возвращать коментарий по его id")
    @Test
    void shouldGetComment() {
        assertThat(commentJpa.getById(1l))
                .isEqualTo(new Comment(1l, 1l, "Книга Пушкина А.С. \"Руслан и Людмила\", жанр Классика"));
    }

    @DisplayName("добавлять коментарий в БД")
    @Test
    void shouldInsertComment() {
        var expectedComment = new Comment(5l, 1l, "еще 1 коментарий");
        commentJpa.save(expectedComment);
        var actualComment = commentJpa.getById(5l);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("менять коментарий в БД")
    @Test
    void shouldUpdateComment() {
        var expectedComment = commentJpa.getById(2l);
        expectedComment.setComment("updatedComment");
        commentJpa.save(expectedComment);
        var actualComment = commentJpa.getById(2l);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("удалять коментарий в БД по id")
    @Test
    void shouldDeleteComment() {
        commentJpa.delete(1l);
        assertThat(commentJpa.getById(1l))
                .isNull();
    }
}