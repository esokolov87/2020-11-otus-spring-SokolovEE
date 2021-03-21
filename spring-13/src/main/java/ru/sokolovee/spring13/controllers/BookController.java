package ru.sokolovee.spring13.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sokolovee.spring13.entities.Book;
import ru.sokolovee.spring13.entities.Comment;
import ru.sokolovee.spring13.repositories.AuthorRepository;
import ru.sokolovee.spring13.repositories.BookRepository;
import ru.sokolovee.spring13.repositories.CommentRepository;
import ru.sokolovee.spring13.repositories.GenreRepository;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@Transactional
public class BookController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/bookList")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/bookInfo/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("book", book);
        model.addAttribute("comments", commentRepository.findAllByBook(book));
        model.addAttribute("newcomment", new Comment(0l));
        return "book";
    }

    @Secured( "ROLE_ADMIN" )
    @PostMapping("/createBook")
    public String createBook(Book book, Model model) {
        book = bookRepository.save(book);
        model.addAttribute("book", book);
        return "redirect:/bookInfo/" + book.getId();
    }

    @Secured( "ROLE_ADMIN" )
    @PostMapping("/addBook")
    public String addBook(Model model) {
        Book book = new Book();
        book.setId(0l);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "addBook";
    }


}
