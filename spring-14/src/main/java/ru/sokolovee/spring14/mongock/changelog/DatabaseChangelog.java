package ru.sokolovee.spring14.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.sokolovee.spring14.documents.Author;
import ru.sokolovee.spring14.documents.Book;
import ru.sokolovee.spring14.documents.Comment;
import ru.sokolovee.spring14.documents.Genre;
import ru.sokolovee.spring14.repositories.mongo.AuthorRepository;
import ru.sokolovee.spring14.repositories.mongo.BookRepository;
import ru.sokolovee.spring14.repositories.mongo.CommentRepository;
import ru.sokolovee.spring14.repositories.mongo.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "sokolovee", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "sokolovee")
    public void insertAuthors(AuthorRepository authorRepository) {
        authorRepository.save(new Author(1l, "Пушкин А.С."));
        authorRepository.save(new Author(2l, "Стивен Кинг"));
        authorRepository.save(new Author(3l, "Джорж Мартин"));
        authorRepository.save(new Author(4l, "Стивен Хокинг"));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "sokolovee")
    public void insertGenres(GenreRepository genreRepository) {
        genreRepository.save(new Genre(1l, "Фантастика"));
        genreRepository.save(new Genre(2l, "Научная литература"));
        genreRepository.save(new Genre(3l, "Ужасы"));
        genreRepository.save(new Genre(4l, "Классика"));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "sokolovee")
    public void insertBooks(BookRepository bookRepository) {
        bookRepository.save(new Book(1l, "Руслан и Людмила", new Author(1l), new Genre(4l)));
        bookRepository.save(new Book(2l, "Игра престолов", new Author(3l), new Genre(1l)));
        bookRepository.save(new Book(3l, "Краткая история времени", new Author(4l), new Genre(2l)));
        bookRepository.save(new Book(4l, "Оно", new Author(2l), new Genre(3)));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "sokolovee")
    public void insertComments(CommentRepository commentRepository) {
        commentRepository.save(new Comment(1l, new Book(1l), "Книга Пушкина А.С. \"Руслан и Людмила\", жанр Классика"));
        commentRepository.save(new Comment(2l, new Book(3l), "Стивен Хокинга \"Краткая история времени\", жанр Научная литература"));
        commentRepository.save(new Comment(3l, new Book(4l), "Книга Стивен Кинга \"Оно\", жанр Ужасы"));
        commentRepository.save(new Comment(4l, new Book(2l), "Джорж Мартина \"Игра престолов\", жанр Фантастика"));
    }

}
