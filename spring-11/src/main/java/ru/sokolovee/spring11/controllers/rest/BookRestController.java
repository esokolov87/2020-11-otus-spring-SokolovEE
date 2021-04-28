package ru.sokolovee.spring11.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sokolovee.spring11.documents.Book;
import ru.sokolovee.spring11.repositories.BookRepository;


@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;
    private static Long lastBookId = 5l;

    @GetMapping("/rest/books")
    public Flux<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/rest/createBook")
    public Mono<Book> createBook(@RequestBody Book book) {
        System.out.println(book);
        book.setId(lastBookId++);
        return bookRepository.save(book);
    }

    @PatchMapping("/book/{id}")
    public Mono<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookRepository.findById(id).flatMap(book1 -> {
            book1.setName(book.getName());
            book1.setAuthor(book.getAuthor());
            book1.setGenre(book.getGenre());
            return Mono.just(book1);
        }).flatMap(b -> bookRepository.save(b));
    }

    @DeleteMapping("/book/{id}")
    public Mono<Void> deleteBook(@PathVariable Long id) {
        return bookRepository.deleteById(id);

    }
}
