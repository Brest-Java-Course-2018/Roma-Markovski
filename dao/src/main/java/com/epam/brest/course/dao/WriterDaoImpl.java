package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Implementation of WriterDao.
 */
public class WriterDaoImpl implements WriterDao {

    /**
     * Named parameters JDBC template.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //constant fields

    private static final String WRITER_ID = "writer_id";

    private static final String WRITER_NAME = "writer_name";

    private static final String WRITER_COUNTRY = "writer_country";

    /**
     * SQL Select-all String.
     */
    @Value("${writer.select}")
    private String GET_WRITERS_SQL;

    /**
     * SQL Select-By-Id String.
     */
    @Value("${writer.selectById}")
    private String GET_WRITER_BY_ID_SQL;

    /**
     * SQL Insert String.
     */
    @Value("${writer.insert}")
    private String ADD_WRITER_SQL;

    /**
     * Sql Update String.
     */
    @Value("${writer.update}")
    private String UPDATE_WRITER_SQL;

    /**
     * Sql Delete String.
     */
    @Value("${writer.delete}")
    private String DELETE_WRITER_SQL;

    /**
     * Setter.
     * @param namedParameterJdbcTemplate
     */
    public void setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Collection<Writer> getWriters() {
        Collection<Writer> writers =
                namedParameterJdbcTemplate.
                        query(GET_WRITERS_SQL, new WriterRowMapper());
        return writers;
    }

    @Override
    public final Writer getWriterById(final Integer writerId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_ID, writerId);
        Writer writer = namedParameterJdbcTemplate.queryForObject(
                GET_WRITER_BY_ID_SQL, namedParameters, new WriterRowMapper()
        );
        return writer;
    }

    @Override
    public final Writer addWriter(final Writer writer) {
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_NAME,
                        writer.getWriterName());
        namedParameters.addValue(WRITER_COUNTRY,
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
                new MapSqlParameterSource(WRITER_NAME,
                        writer.getWriterName());
        namedParameters.addValue(WRITER_COUNTRY, writer.getWriterCountry());
        namedParameters.addValue(WRITER_ID, writer.getWriterId());
        namedParameterJdbcTemplate.update(UPDATE_WRITER_SQL, namedParameters);
    }

    @Override
    public final void deleteWriterById(final Integer writerId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_ID, writerId);
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
            writer.setWriterId(resultSet.getInt(WRITER_ID));
            writer.setWriterName(resultSet.getString(WRITER_NAME));
            writer.setWriterCountry(resultSet.getString(WRITER_COUNTRY));
            return writer;
        }
    }
}
