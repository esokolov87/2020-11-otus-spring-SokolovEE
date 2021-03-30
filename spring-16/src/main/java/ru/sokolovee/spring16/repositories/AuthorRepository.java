package ru.sokolovee.spring16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring16.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
