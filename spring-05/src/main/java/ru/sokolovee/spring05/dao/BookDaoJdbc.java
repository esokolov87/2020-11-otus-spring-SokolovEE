package ru.sokolovee.spring05.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring05.domain.Book;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(id) from BOOK", (Map<String, ?>) null, Integer.class);
    }

    @Override
    public void insert(long id, String name, long authorId, long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("author_id", authorId);
        params.put("genre_id", genreId);
        jdbcTemplate.update("insert into book (id, name, author_id, genre_id) values (:id, :name, :author_id, :genre_id)", params);
    }

    @Override
    public void update(long id, String name, long authorId, long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("author_id", authorId);
        params.put("genre_id", genreId);
        jdbcTemplate.update("update book set name=:name, author_id=:author_id, genre_id=:genre_id where id=:id", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcTemplate.queryForObject(
                "select b.id, b.name, b.author_id, a.name as aname, b.genre_id, g.name as gname " +
                        "from book b " +
                        "join author a ON b.author_id = a.id " +
                        "join genre g ON b.genre_id=g.id " +
                        "where b.id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(
                "select b.id, b.name, b.author_id, a.name as aname, b.genre_id, g.name as gname " +
                "from book b " +
                "join author a ON b.author_id = a.id " +
                "join genre g ON b.genre_id=g.id ", new BookMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcTemplate.update(
                "delete from book where id = :id", params
        );
    }
}
