package ru.sokolovee.spring07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring07.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
