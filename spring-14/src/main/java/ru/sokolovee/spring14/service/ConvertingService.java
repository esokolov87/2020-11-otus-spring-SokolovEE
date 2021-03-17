package ru.sokolovee.spring14.service;

import org.springframework.stereotype.Service;
import ru.sokolovee.spring14.entities.Author;
import ru.sokolovee.spring14.entities.Book;
import ru.sokolovee.spring14.entities.Comment;
import ru.sokolovee.spring14.entities.Genre;

@Service
public class ConvertingService {

    public Book convertBook(ru.sokolovee.spring14.documents.Book mongoBook){
        return new Book(null, mongoBook.getName(),new Author(mongoBook.getAuthor().getId()), new Genre(mongoBook.getGenre().getId()),null);
    }

    public Author convertAuthor(ru.sokolovee.spring14.documents.Author mongoAuthor){
        return new Author(mongoAuthor.getId(), mongoAuthor.getName());
    }

    public Genre convertGenre(ru.sokolovee.spring14.documents.Genre mongoGenre){
        return new Genre(mongoGenre.getId(), mongoGenre.getName());
    }

    public Comment convertComment(ru.sokolovee.spring14.documents.Comment mongoComment){
        return new Comment(mongoComment.getId(), new Book(mongoComment.getBook().getId()), mongoComment.getComment());
    }
}
