package ru.sokolovee.spring13.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring13.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
