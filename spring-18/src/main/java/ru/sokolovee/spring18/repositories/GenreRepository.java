package ru.sokolovee.spring18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring18.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
