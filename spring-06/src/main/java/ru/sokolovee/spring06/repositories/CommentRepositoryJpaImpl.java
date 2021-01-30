package ru.sokolovee.spring06.repositories;

import org.springframework.stereotype.Repository;
import ru.sokolovee.spring06.entities.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void insert(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void update(Comment comment) {
        em.merge(comment);
    }

    @Override
    public void delete(Long id) {
        em.remove(getById(id));
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

}
