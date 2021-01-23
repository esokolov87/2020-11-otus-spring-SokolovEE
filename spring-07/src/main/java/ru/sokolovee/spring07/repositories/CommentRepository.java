package ru.sokolovee.spring07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring07.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
