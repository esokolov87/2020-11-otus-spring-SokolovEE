package ru.sokolovee.spring05.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sokolovee.spring05.dao.BookDao;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ShellComands {

    private final BookDao bookDao;


    @ShellMethod(value = "get book command", key = {"getBook", "gb"})
    public String getBook(@ShellOption(defaultValue = "1") long id) {
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "get all books command", key = {"getAllBooks", "gab"})
    public String getAll() {
        return bookDao.getAll().stream()
                .map(s -> s.toString())
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "insert book command", key = {"insertBook", "ib"})
    public String createBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookDao.insert(id, name, authorId, genreId);
        return "Книга создана: " + bookDao.getById(id).toString();
    }

    @ShellMethod(value = "delete book command", key = {"deleteBook", "db"})
    public String deleteBook(@ShellOption long id) {
        bookDao.deleteById(id);
        return "Книга удалена";
    }

    @ShellMethod(value = "update book command", key = {"updateBook", "ub"})
    public String updateBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookDao.update(id, name, authorId, genreId);
        return "Книга изменена: " + bookDao.getById(id).toString();
    }

    @ShellMethod(value = "count book command", key = {"countBook", "cb"})
    public String countBook() {

        return "Всего книг: " + bookDao.count();
    }

}
