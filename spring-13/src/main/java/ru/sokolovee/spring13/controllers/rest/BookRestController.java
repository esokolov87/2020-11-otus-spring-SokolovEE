package ru.sokolovee.spring13.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sokolovee.spring13.entities.Book;
import ru.sokolovee.spring13.repositories.AuthorRepository;
import ru.sokolovee.spring13.repositories.BookRepository;
import ru.sokolovee.spring13.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/book/{id}")
    public void updateBook(@PathVariable Long id, @RequestParam String name, @RequestParam Long authorid, @RequestParam Long genreid) {
        Book book = bookRepository.getOne(id);
        book.setName(name);
        book.setAuthor(authorRepository.getOne(authorid));
        book.setGenre(genreRepository.getOne(genreid));
        bookRepository.save(book);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

}
