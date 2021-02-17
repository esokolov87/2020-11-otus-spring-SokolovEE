package ru.sokolovee.spring09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring09.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}