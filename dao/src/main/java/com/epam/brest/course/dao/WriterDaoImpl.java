package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Implementation of WriterDao.
 */
public class WriterDaoImpl implements WriterDao {

    /**
     * JDBC template.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Named parameters JDBC template.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SQL Select-all String.
     */
    private static final String GET_WRITERS_SQL = "SELECT writer_id, "
            + "writer_name, writer_country FROM writer";

    /**
     * SQL Select-By-Id String.
     */
    private static final String GET_WRITER_BY_ID_SQL = "SELECT "
            + "writer_id, writer_name, writer_country FROM writer "
            + "WHERE writer_id = :id";

    /**
     * SQL Insert String.
     */
    private static final String ADD_WRITER_SQL = "INSERT INTO writer "
            + "(writer_name, writer_country) VALUES (:writer_name, "
            + ":writer_country)";

    /**
     * Sql Update String.
     */
    private static final String UPDATE_WRITER_SQL = "UPDATE writer "
            + "SET writer_name = :writer_name, writer_country = "
            + ":writer_country WHERE writer_id= :writer_id";

    /**
     * Sql Delete String.
     */
    private static final String DELETE_WRITER_SQL = "DELETE FROM writer "
            + "WHERE writer_id = :id";

    /**
     * Constructor with data source.
     * @param dataSource - data source.
     */
    public WriterDaoImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public final Collection<Writer> getWriters() {
        Collection<Writer> writers =
                jdbcTemplate.query(GET_WRITERS_SQL, new WriterRowMapper());
        return writers;
    }

    @Override
    public final Writer getWriterById(final Integer writerId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("id", writerId);
        Writer writer = namedParameterJdbcTemplate.queryForObject(
                GET_WRITER_BY_ID_SQL, namedParameters, new WriterRowMapper()
        );
        return writer;
    }

    @Override
    public final Writer addWriter(final Writer writer) {
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("writer_name",
                        writer.getWriterName());
        namedParameters.addValue("writer_country",
                writer.getWriterCountry());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_WRITER_SQL,
                namedParameters, generatedKeyHolder);
        writer.setWriterId(generatedKeyHolder.getKey().intValue());
        return writer;
    }

    @Override
    public final void updateWriter(final Writer writer) {
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("writer_name",
                        writer.getWriterName());
        namedParameters.addValue("writer_country", writer.getWriterCountry());
        namedParameters.addValue("writer_id", writer.getWriterId());
        namedParameterJdbcTemplate.update(UPDATE_WRITER_SQL, namedParameters);
    }

    @Override
    public final void deleteWriterById(final Integer writerId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("id", writerId);
        namedParameterJdbcTemplate.update(
                DELETE_WRITER_SQL, namedParameters);
    }

    /**
     * WriterRowMapper  - for creating models from resultSet.
     */
    private class WriterRowMapper implements RowMapper<Writer> {

        @Override
        public final Writer mapRow(
                final ResultSet resultSet, final int i)
                throws SQLException {
            Writer writer = new Writer();
            writer.setWriterId(resultSet.getInt(1));
            writer.setWriterName(resultSet.getString(2));
            writer.setWriterCountry(resultSet.getString(3));
            return writer;
        }
    }
}
