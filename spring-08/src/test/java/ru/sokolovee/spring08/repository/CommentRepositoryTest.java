package ru.sokolovee.spring08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sokolovee.spring08.documents.Comment;
import ru.sokolovee.spring08.repositories.BookRepository;
import ru.sokolovee.spring08.repositories.CommentRepository;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repository для работы с жанрами должно")
@DataMongoTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @DisplayName("возвращать коментарий по его id")
    @Test
    void shouldGetComment() {
        assertThat(commentRepository.findById(1l).orElse(null))
                .isEqualTo(mongoTemplate.findById(1l, Comment.class));
    }

    @DisplayName("добавлять коментарий в БД")
    @Test
    void shouldInsertComment() {
        var expectedComment = new Comment(5l, bookRepository.findById(1l).orElse(null), "еще 1 коментарий");
        commentRepository.save(expectedComment);
        var actualComment = commentRepository.findById(5l).orElse(null);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("менять коментарий в БД")
    @Test
    void shouldUpdateComment() {
        var expectedComment = commentRepository.findById(2l).orElse(null);
        expectedComment.setComment("updatedComment");
        commentRepository.save(expectedComment);
        var actualComment = commentRepository.findById(2l).orElse(null);
        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @DisplayName("удалять коментарий в БД по id")
    @Test
    void shouldDeleteComment() {
        commentRepository.deleteById(1l);
        assertThat(commentRepository.findById(1l).orElse(null))
                .isNull();
    }
}