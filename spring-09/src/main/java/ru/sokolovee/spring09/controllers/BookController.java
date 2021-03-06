package ru.sokolovee.spring09.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sokolovee.spring09.entities.Book;
import ru.sokolovee.spring09.entities.Comment;
import ru.sokolovee.spring09.repositories.AuthorRepository;
import ru.sokolovee.spring09.repositories.BookRepository;
import ru.sokolovee.spring09.repositories.CommentRepository;
import ru.sokolovee.spring09.repositories.GenreRepository;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@Transactional
public class BookController {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping("/bookList")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());

        return "bookList";
    }

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

    @PostMapping("/createBook")
    public String createBook(Book book, Model model) {
        book = bookRepository.save(book);
        model.addAttribute("book", book);
        return "redirect:/bookInfo/" + book.getId();
    }

    @PostMapping("/editBook/{id}")
    public String updateBook(Book book, Model model) {
        book = bookRepository.save(book);
        //model.addAttribute("book", book);
        return "redirect:/bookInfo/{id}";
    }

    @PostMapping(value = "/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/bookList";
    }

    @PostMapping("/addBook")
    public String addBook(Model model) {
        Book book = new Book();//bookRepository.save(book);
        book.setId(0l);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());

        return "addBook";
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable Long id, String comment) {
        Comment c = new Comment();
        c.setId(0l);
        c.setBook(bookRepository.getOne(id));
        c.setComment(comment);
        commentRepository.save(c);
        return "redirect:/bookInfo/" + id;
    }

    @GetMapping(value = "/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id) {
        Long bookId = commentRepository.getOne(id).getBook().getId();
        commentRepository.deleteById(id);
        return "redirect:/bookInfo/" + bookId;
    }

}
