package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class WriterDaoImpl implements WriterDao {

    JdbcTemplate jdbcTemplate;

    private final String GET_WRITERS_SQL = "SELECT writer_id, writer_name, writer_country FROM writer";

    public WriterDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Writer> getWriters() {
        Collection<Writer> writers = jdbcTemplate.query(GET_WRITERS_SQL, new WriterRowMapper());
        return writers;
    }

    @Override
    public Writer getWriterById(Integer writerId) {
        return null;
    }

    private class WriterRowMapper implements RowMapper<Writer> {

        @Override
        public Writer mapRow(ResultSet resultSet, int i) throws SQLException {
            Writer writer = new Writer();
            writer.setWriterId(resultSet.getInt(1));
            writer.setWriterName(resultSet.getString(2));
            writer.setWriterCountry(resultSet.getString(3));
            return null;
        }
    }
}
