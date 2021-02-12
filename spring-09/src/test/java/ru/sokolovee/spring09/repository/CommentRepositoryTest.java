package ru.sokolovee.spring09.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.sokolovee.spring09.entities.Comment;
import ru.sokolovee.spring09.repositories.BookRepository;
import ru.sokolovee.spring09.repositories.CommentRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами должно")
@DataJpaTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentJpa;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private EntityManager em;

    @DisplayName("возвращать коментарий по его id")
    @Test
    void shouldGetComment() {
        assertThat(commentJpa.findById(1l).orElse(null))
                .isEqualTo(em.find(Comment.class, 1l));
    }

    @DisplayName("добавлять коментарий в БД")
    @Test
    void shouldInsertComment() {
        var expectedComment = new Comment(5l, bookRepository.findById(1l).orElse(null), "еще 1 коментарий");
        commentJpa.save(expectedComment);
        var actualComment = commentJpa.findById(5l).orElse(null);
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
        var actualComment = commentJpa.findById(2l).orElse(null);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("удалять коментарий в БД по id")
    @Test
    void shouldDeleteComment() {
        commentJpa.deleteById(1l);
        assertThat(commentJpa.findById(1l).orElse(null))
                .isNull();
    }
}