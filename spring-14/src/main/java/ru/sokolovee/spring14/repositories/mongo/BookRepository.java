package ru.sokolovee.spring14.repositories.mongo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring14.documents.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
