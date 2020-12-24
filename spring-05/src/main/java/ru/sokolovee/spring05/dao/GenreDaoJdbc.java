package ru.sokolovee.spring05.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sokolovee.spring05.domain.Genre;

import java.util.Collections;
import java.util.Map;

@Repository
@AllArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcTemplate.queryForObject(
                "select * from genre where id = :id", params, new GenreMapper()
        );
    }
}
