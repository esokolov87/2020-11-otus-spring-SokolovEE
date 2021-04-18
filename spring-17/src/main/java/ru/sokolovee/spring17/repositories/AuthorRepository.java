package ru.sokolovee.spring17.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring17.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
