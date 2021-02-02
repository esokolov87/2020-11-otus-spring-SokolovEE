package ru.sokolovee.spring08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring08.documents.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
