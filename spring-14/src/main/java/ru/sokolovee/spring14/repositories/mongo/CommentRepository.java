package ru.sokolovee.spring14.repositories.mongo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring14.documents.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
