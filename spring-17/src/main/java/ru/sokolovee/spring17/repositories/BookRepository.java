package ru.sokolovee.spring17.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring17.entities.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "book-entity-graph")
    List<Book> findAll();
}
