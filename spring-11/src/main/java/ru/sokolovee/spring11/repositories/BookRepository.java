package ru.sokolovee.spring11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring11.documents.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, Long> {
}
