package ru.sokolovee.spring07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring07.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
