package ru.sokolovee.spring14.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring14.entities.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
