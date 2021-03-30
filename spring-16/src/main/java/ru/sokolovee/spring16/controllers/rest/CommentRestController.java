package ru.sokolovee.spring16.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sokolovee.spring16.entities.Comment;
import ru.sokolovee.spring16.repositories.BookRepository;
import ru.sokolovee.spring16.repositories.CommentRepository;

@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @DeleteMapping("/comment/{id}")
    public void updateBook(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }

    @PostMapping("book/{bookId}/comment")
    public Long newComment(@PathVariable Long bookId, @RequestParam String comment) {
        Comment c = new Comment(null, bookRepository.getOne(bookId), comment);
        c = commentRepository.save(c);
        return c.getId();
    }
}
