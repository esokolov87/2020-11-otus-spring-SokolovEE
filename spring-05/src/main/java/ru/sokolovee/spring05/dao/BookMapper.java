package ru.sokolovee.spring05.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.sokolovee.spring05.domain.Author;
import ru.sokolovee.spring05.domain.Book;
import ru.sokolovee.spring05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
class BookMapper implements RowMapper<Book> {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Author author = authorDao.getById(resultSet.getLong("author_id"));
        Genre genre = genreDao.getById(resultSet.getLong("genre_id"));
        return new Book(id, name, author, genre);
    }
}