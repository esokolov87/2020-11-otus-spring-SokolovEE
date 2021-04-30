package ru.sokolovee.spring18.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sokolovee.spring18.entities.Book;
import ru.sokolovee.spring18.entities.Comment;
import ru.sokolovee.spring18.repositories.AuthorRepository;
import ru.sokolovee.spring18.repositories.BookRepository;
import ru.sokolovee.spring18.repositories.CommentRepository;
import ru.sokolovee.spring18.repositories.GenreRepository;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;


    @GetMapping("/bookList")
    @Bulkhead(name = "bookController", fallbackMethod = "fallbackBook", type = Bulkhead.Type.SEMAPHORE)
    public String getAllBooks(Model model) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2000));
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("/bookInfo/{id}")
    @Bulkhead(name = "bookController", fallbackMethod = "fallbackBook", type = Bulkhead.Type.SEMAPHORE)
    public String getBook(@PathVariable("id") Long id, Model model) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2000));
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("book", book);
        model.addAttribute("comments", commentRepository.findAllByBook(book));
        model.addAttribute("newcomment", new Comment(0l));
        return "book";
    }

    @PostMapping("/createBook")
    @Bulkhead(name = "bookController", fallbackMethod = "fallbackBook", type = Bulkhead.Type.SEMAPHORE)
    public String createBook(Book book, Model model) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2000));
        book = bookRepository.save(book);
        model.addAttribute("book", book);
        return "redirect:/bookInfo/" + book.getId();
    }

    @PostMapping("/addBook")
    @Bulkhead(name = "bookController", fallbackMethod = "fallbackBook", type = Bulkhead.Type.SEMAPHORE)
    public String addBook(Model model) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2000));
        Book book = new Book();
        book.setId(0l);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "addBook";
    }

    @GetMapping("/errorPage")
    public String errorPage() {
        return "errorPage";
    }

    public String fallbackBook(Exception e) {
        log.info("bulkHeadBook bookController");
        return "errorPage";
    }

}
