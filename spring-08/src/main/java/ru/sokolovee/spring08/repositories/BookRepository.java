package ru.sokolovee.spring08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring08.documents.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
