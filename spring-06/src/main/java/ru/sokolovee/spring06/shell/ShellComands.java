package ru.sokolovee.spring06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sokolovee.spring06.entities.Author;
import ru.sokolovee.spring06.entities.Book;
import ru.sokolovee.spring06.entities.Comment;
import ru.sokolovee.spring06.entities.Genre;
import ru.sokolovee.spring06.repositories.BookRepositoryJpaImpl;
import ru.sokolovee.spring06.repositories.CommentRepositoryJpaImpl;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
@Transactional
public class ShellComands {

    private final BookRepositoryJpaImpl bookRepository;
    private final CommentRepositoryJpaImpl commentRepository;


    @ShellMethod(value = "get book command", key = {"getBook", "gb"})
    public String getBook(@ShellOption(defaultValue = "1") long id) {
        return bookRepository.getById(id).toString();
    }

    @ShellMethod(value = "get all books command", key = {"getAllBooks", "gab"})
    public String getAll() {
        return bookRepository.getAll().stream()
                .map(s -> s.toString())
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "insert book command", key = {"insertBook", "ib"})
    public String createBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookRepository.insert(new Book(id, name, new Author(authorId, null), new Genre(genreId, null), null));
        return "Книга создана: " + bookRepository.getById(id).toString();
    }

    @ShellMethod(value = "delete book command", key = {"deleteBook", "db"})
    public String deleteBook(@ShellOption long id) {
        bookRepository.deleteById(id);
        return "Книга удалена";
    }

    @ShellMethod(value = "update book command", key = {"updateBook", "ub"})
    public String updateBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookRepository.update(new Book(id, name, new Author(authorId, null), new Genre(genreId, null),null));
        return "Книга изменена: " + bookRepository.getById(id).toString();
    }

    @ShellMethod(value = "count book command", key = {"countBook", "cb"})
    public String countBook() {
        return "Всего книг: " + bookRepository.count();
    }

    @ShellMethod(value = "create comment command", key = {"createComment", "cc"})
    public String createComment(@ShellOption Long id, @ShellOption String comment) {
        commentRepository.insert(new Comment(null,bookRepository.getById(id), comment));
        return "Коментарий добавлен";
    }

    @ShellMethod(value = "get comment command", key = {"getComment", "gc"})
    public String getComment(@ShellOption Long id) {
        return commentRepository.getById(id).toString();
    }

    @ShellMethod(value = "update comment command", key = {"updateComment", "uc"})
    public String updateComment(@ShellOption Long id, @ShellOption String newComment) {
        Comment comment = commentRepository.getById(id);
        comment.setComment(newComment);
        commentRepository.update(comment);
        return "Коментарий обновлен";
    }

    @ShellMethod(value = "delete comment command", key = {"deleteComment", "dc"})
    public String deleteComment(@ShellOption Long id) {
        commentRepository.delete(id);
        return "Коментарий удален";
    }

    @ShellMethod(value = "get comments by bookId command", key = {"getCommentByBookId", "gcbb"})
    public String getCommentsByBookId(@ShellOption Long id) {
        Book book = bookRepository.getById(id);
        return book.getComments().toString();
    }


}
