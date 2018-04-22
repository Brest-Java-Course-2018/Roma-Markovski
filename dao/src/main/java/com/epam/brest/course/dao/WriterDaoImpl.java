package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class WriterDaoImpl implements WriterDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String GET_WRITERS_SQL = "SELECT writer_id, writer_name, writer_country FROM writer";

    private final String GET_WRITER_BY_ID_SQL = "SELECT writer_id, writer_name, writer_country FROM writer " +
            "WHERE writer_id = :id";

    public WriterDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Collection<Writer> getWriters() {
        Collection<Writer> writers = jdbcTemplate.query(GET_WRITERS_SQL, new WriterRowMapper());
        return writers;
    }

    @Override
    public Writer getWriterById(Integer writerId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", writerId);
        Writer writer = namedParameterJdbcTemplate.queryForObject(
                GET_WRITER_BY_ID_SQL, namedParameters, new WriterRowMapper()
        );
        return writer;
    }

    private class WriterRowMapper implements RowMapper<Writer> {

        @Override
        public Writer mapRow(ResultSet resultSet, int i) throws SQLException {
            Writer writer = new Writer();
            writer.setWriterId(resultSet.getInt(1));
            writer.setWriterName(resultSet.getString(2));
            writer.setWriterCountry(resultSet.getString(3));
            return writer;
        }
    }
}
