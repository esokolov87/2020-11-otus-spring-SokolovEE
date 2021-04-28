package ru.sokolovee.spring11.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sokolovee.spring11.documents.Author;
import ru.sokolovee.spring11.documents.Book;
import ru.sokolovee.spring11.documents.Comment;
import ru.sokolovee.spring11.documents.Genre;
import ru.sokolovee.spring11.repositories.AuthorRepository;
import ru.sokolovee.spring11.repositories.BookRepository;
import ru.sokolovee.spring11.repositories.CommentRepository;
import ru.sokolovee.spring11.repositories.GenreRepository;


@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "sokolovee", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "sokolovee", runAlways = true)
    public void insertAuthors(@Autowired AuthorRepository authorRepository) {

        authorRepository.save(new Author(1l, "Пушкин А.С.")).subscribe();
        authorRepository.save(new Author(2l, "Стивен Кинг")).subscribe();
        authorRepository.save(new Author(3l, "Джорж Мартин")).subscribe();
        authorRepository.save(new Author(4l, "Стивен Хокинг")).subscribe();
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "sokolovee", runAlways = true)
    public void insertGenres(GenreRepository genreRepository) {
        genreRepository.save(new Genre(1l, "Фантастика")).subscribe();
        genreRepository.save(new Genre(2l, "Научная литература")).subscribe();
        genreRepository.save(new Genre(3l, "Ужасы")).subscribe();
        genreRepository.save(new Genre(4l, "Классика")).subscribe();
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "sokolovee", runAlways = true)
    public void insertBooks(BookRepository bookRepository) {
        bookRepository.save(new Book(1l, "Руслан и Людмила", new Author(1l), new Genre(4l))).subscribe();
        bookRepository.save(new Book(2l, "Игра престолов", new Author(3l), new Genre(1l))).subscribe();
        bookRepository.save(new Book(3l, "Краткая история времени", new Author(4l), new Genre(2l))).subscribe();
        bookRepository.save(new Book(4l, "Оно", new Author(2l), new Genre(3))).subscribe();
    }

    @ChangeSet(order = "005", id = "insertComments", author = "sokolovee", runAlways = true)
    public void insertComments(CommentRepository commentRepository) {
        commentRepository.save(new Comment(1l, new Book(1l), "Книга Пушкина А.С. \"Руслан и Людмила\", жанр Классика")).subscribe();
        commentRepository.save(new Comment(2l, new Book(3l), "Стивен Хокинга \"Краткая история времени\", жанр Научная литература")).subscribe();
        commentRepository.save(new Comment(3l, new Book(4l), "Книга Стивен Кинга \"Оно\", жанр Ужасы")).subscribe();
        commentRepository.save(new Comment(4l, new Book(2l), "Джорж Мартина \"Игра престолов\", жанр Фантастика")).subscribe();
    }
}
