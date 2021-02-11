package ru.sokolovee.spring10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring10.entities.Book;
import ru.sokolovee.spring10.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);
}
