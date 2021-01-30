package ru.sokolovee.spring07.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sokolovee.spring07.entities.Author;
import ru.sokolovee.spring07.entities.Book;
import ru.sokolovee.spring07.entities.Comment;
import ru.sokolovee.spring07.entities.Genre;
import ru.sokolovee.spring07.repositories.BookRepository;
import ru.sokolovee.spring07.repositories.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
@Transactional
public class ShellComands {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;


    @ShellMethod(value = "get book command", key = {"getBook", "gb"})
    public String getBook(@ShellOption(defaultValue = "1") long id) {
        return bookRepository.findById(id).orElse(null).toString();
    }

    @ShellMethod(value = "get all books command", key = {"getAllBooks", "gab"})
    public String getAll() {
        return bookRepository.findAll().stream()
                .map(s -> s.toString())
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "insert book command", key = {"insertBook", "ib"})
    public String createBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookRepository.save(new Book(id, name, new Author(authorId, null), new Genre(genreId, null), null));
        return "Книга создана: " + bookRepository.findById(id).orElse(null).toString();
    }

    @ShellMethod(value = "delete book command", key = {"deleteBook", "db"})
    public String deleteBook(@ShellOption long id) {
        bookRepository.deleteById(id);
        return "Книга удалена";
    }

    @ShellMethod(value = "update book command", key = {"updateBook", "ub"})
    public String updateBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookRepository.save(new Book(id, name, new Author(authorId, null), new Genre(genreId, null),null));
        return "Книга изменена: " + bookRepository.findById(id).orElse(null).toString();
    }

    @ShellMethod(value = "count book command", key = {"countBook", "cb"})
    public String countBook() {
        return "Всего книг: " + bookRepository.count();
    }

    @ShellMethod(value = "create comment command", key = {"createComment", "cc"})
    public String createComment(@ShellOption Long id, @ShellOption String comment) {
        commentRepository.save(new Comment(null,bookRepository.findById(id).orElseThrow(), comment));
        return "Коментарий добавлен";
    }

    @ShellMethod(value = "get comment command", key = {"getComment", "gc"})
    public String getComment(@ShellOption Long id) {
        return commentRepository.findById(id).orElse(null).toString();
    }

    @ShellMethod(value = "update comment command", key = {"updateComment", "uc"})
    public String updateComment(@ShellOption Long id, @ShellOption String newComment) {
        Comment comment = commentRepository.findById(id).orElse(null);
        comment.setComment(newComment);
        commentRepository.save(comment);
        return "Коментарий обновлен";
    }

    @ShellMethod(value = "delete comment command", key = {"deleteComment", "dc"})
    public String deleteComment(@ShellOption Long id) {
        commentRepository.deleteById(id);
        return "Коментарий удален";
    }

    @ShellMethod(value = "get comments by bookId command", key = {"getCommentByBookId", "gcbb"})
    public List<Comment> getCommentsByBookId(@ShellOption Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return book.getComments();
    }
}
