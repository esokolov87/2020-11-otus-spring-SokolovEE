package ru.sokolovee.spring05.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring05.domain.Author;

import java.util.Collections;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcTemplate.queryForObject(
                "select * from author where id = :id", params, new AuthorMapper()
        );
    }
}
