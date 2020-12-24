package ru.sokolovee.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.sokolovee.spring05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Genre(id, name);
    }
}