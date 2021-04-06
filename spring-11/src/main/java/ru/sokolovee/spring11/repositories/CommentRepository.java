package ru.sokolovee.spring11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sokolovee.spring11.documents.Book;
import ru.sokolovee.spring11.documents.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {
    Flux<Comment> findAllByBook(Mono<Book> book);
}
