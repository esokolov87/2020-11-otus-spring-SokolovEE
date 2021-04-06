package ru.sokolovee.spring11.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.sokolovee.spring11.documents.Comment;
import ru.sokolovee.spring11.repositories.BookRepository;
import ru.sokolovee.spring11.repositories.CommentRepository;

@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    private static Long lastCommentId = 5l;

    @DeleteMapping("/comment/{id}")
    public Mono<Void> deleteComment(@PathVariable Long id) {
        return commentRepository.deleteById(id);
    }

    @PostMapping("book/{bookId}/comment")
    public Mono<Long> newComment(@PathVariable Long bookId, @RequestBody String comment) {
        return bookRepository.findById(bookId).flatMap(book -> {
            Comment c = new Comment(lastCommentId++, book, comment);
            return commentRepository.save(c);
        }).map(comment1 -> comment1.getId());
    }
}
