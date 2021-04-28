package ru.sokolovee.spring11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;
import ru.sokolovee.spring11.documents.Book;
import ru.sokolovee.spring11.documents.Comment;
import ru.sokolovee.spring11.repositories.AuthorRepository;
import ru.sokolovee.spring11.repositories.BookRepository;
import ru.sokolovee.spring11.repositories.CommentRepository;
import ru.sokolovee.spring11.repositories.GenreRepository;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping("/bookList")
    @ModelAttribute
    public Mono<String> getAllBooks(Model model) {
        return Mono.just("bookList");
    }

    @GetMapping("/bookInfo/{id}")
    public Mono<String> getBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("book", bookRepository.findById(id));
        model.addAttribute("comments", commentRepository.findAllByBook(bookRepository.findById(id)));
        model.addAttribute("newcomment", new Comment());
        return Mono.just("book");
    }

    @PostMapping("/createBook")
    public Mono<String> createBook(Book book, Model model) {
        book = bookRepository.save(book).block();
        model.addAttribute("book", book);
        return Mono.just("redirect:/bookInfo/" + book.getId());
    }

    @GetMapping("/addBook")
    @ModelAttribute
    public Mono<String> addBook(Model model) {
        Book book = new Book();
        book.setId(0l);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return Mono.just("addBook");
    }

}
