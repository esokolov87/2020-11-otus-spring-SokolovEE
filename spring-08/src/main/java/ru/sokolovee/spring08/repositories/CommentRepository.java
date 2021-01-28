package ru.sokolovee.spring08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring08.documents.Book;
import ru.sokolovee.spring08.documents.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByBook(Book book);
}
