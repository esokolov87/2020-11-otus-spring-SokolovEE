package ru.sokolovee.spring13.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.sokolovee.spring13.entities.Comment;
import ru.sokolovee.spring13.repositories.BookRepository;
import ru.sokolovee.spring13.repositories.CommentRepository;

@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("book/{bookId}/comment")
    public Long newComment(@PathVariable Long bookId, @RequestParam String comment) {
        Comment c = new Comment(null, bookRepository.getOne(bookId), comment);
        c = commentRepository.save(c);
        return c.getId();
    }
}
