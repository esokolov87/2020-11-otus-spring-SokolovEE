package ru.sokolovee.spring06.repositories;

import org.springframework.stereotype.Repository;
import ru.sokolovee.spring06.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthorRepositoryJpaImpl implements  AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }
}
