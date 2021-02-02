package ru.sokolovee.spring08.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sokolovee.spring08.documents.Author;
import ru.sokolovee.spring08.documents.Book;
import ru.sokolovee.spring08.documents.Comment;
import ru.sokolovee.spring08.documents.Genre;
import ru.sokolovee.spring08.repositories.BookRepository;
import ru.sokolovee.spring08.repositories.CommentRepository;

import java.util.Iterator;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor

public class ShellComands {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;


    @ShellMethod(value = "get book command", key = {"getBook", "gb"})
    public String getBook(@ShellOption(defaultValue = "1") long id) {
        return bookRepository.findById(id).toString();
    }

    @ShellMethod(value = "get all books command", key = {"getAllBooks", "gab"})
    public String getAll() {
        String result = "";
        bookRepository.findAll();
        Iterator<Book> ib = bookRepository.findAll().iterator();
        while (ib.hasNext()) {
            result += ib.next().toString() + "\n";
        }
        return result;
    }

    @ShellMethod(value = "insert book command", key = {"insertBook", "ib"})
    public String createBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookRepository.save(new Book(id, name, new Author(authorId), new Genre(genreId)));
        return "Книга создана: " + bookRepository.findById(id).orElse(null).toString();
    }

    @ShellMethod(value = "delete book command", key = {"deleteBook", "db"})
    public String deleteBook(@ShellOption long id) {
        bookRepository.deleteById(id);
        return "Книга удалена";
    }

    @ShellMethod(value = "update book command", key = {"updateBook", "ub"})
    public String updateBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookRepository.save(new Book(id, name, new Author(authorId), new Genre(genreId)));
        return "Книга изменена: " + bookRepository.findById(id).orElse(null).toString();
    }

    @ShellMethod(value = "count book command", key = {"countBook", "cb"})
    public String countBook() {
        return "Всего книг: " + bookRepository.count();
    }

    @ShellMethod(value = "create comment command", key = {"createComment", "cc"})
    public String createComment(@ShellOption Long id, @ShellOption Long bookId, @ShellOption String comment) {
        commentRepository.save(new Comment(id, bookRepository.findById(bookId).orElse(null), comment));
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
        return commentRepository.findByBook(bookRepository.findById(id).get());
    }
}
