package ru.sokolovee.spring07.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring07.entities.Comment;
import ru.sokolovee.spring07.repositories.CommentRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами должно")
@Import(CommentRepository.class)
@DataJpaTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentJpa;
    @Autowired
    private EntityManager em;

    @DisplayName("возвращать коментарий по его id")
    @Test
    void shouldGetComment() {
        assertThat(commentJpa.findById(1l))
                .isEqualTo(new Comment(1l, 1l, "Книга Пушкина А.С. \"Руслан и Людмила\", жанр Классика"));
    }

    @DisplayName("добавлять коментарий в БД")
    @Test
    void shouldInsertComment() {
        var expectedComment = new Comment(5l, 1l, "еще 1 коментарий");
        commentJpa.save(expectedComment);
        var actualComment = commentJpa.findById(5l);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("менять коментарий в БД")
    @Test
    void shouldUpdateComment() {
        var expectedComment = commentJpa.findById(2l).orElse(null);
        expectedComment.setComment("updatedComment");
        commentJpa.save(expectedComment);
        var actualComment = commentJpa.findById(2l);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("удалять коментарий в БД по id")
    @Test
    void shouldDeleteComment() {
        commentJpa.deleteById(1l);
        assertThat(commentJpa.findById(1l))
                .isNull();
    }
}