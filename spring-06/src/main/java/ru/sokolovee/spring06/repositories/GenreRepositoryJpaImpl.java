package ru.sokolovee.spring06.repositories;

import org.springframework.stereotype.Repository;
import ru.sokolovee.spring06.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }
}
