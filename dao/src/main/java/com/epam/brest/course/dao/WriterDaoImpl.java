package com.epam.brest.course.dao;

import com.epam.brest.course.model.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

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
    private String getWritersSql;

    /**
     * SQL Select-By-Id String.
     */
    @Value("${writer.selectById}")
    private String getWriterByIdSql;

    /**
     * SQL Insert String.
     */
    @Value("${writer.insert}")
    private String addWriterSql;

    /**
     * Sql Update String.
     */
    @Value("${writer.update}")
    private String updateWriterSql;

    /**
     * Sql Delete String.
     */
    @Value("${writer.delete}")
    private String deleteWriterSql;

    /**
     * Setter.
     * @param namedParameterJdbcTemplate
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate
                    namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Collection<Writer> getWriters() {
        LOGGER.debug("getWriters()");
        Collection<Writer> writers =
                namedParameterJdbcTemplate.
                        query(getWritersSql, new WriterRowMapper());
        return writers;
    }

    @Override
    public final Writer getWriterById(final Integer writerId) {
        LOGGER.debug("getWritersById({})", writerId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_ID, writerId);
        Writer writer = namedParameterJdbcTemplate.queryForObject(
                getWriterByIdSql, namedParameters, new WriterRowMapper()
        );
        return writer;
    }

    @Override
    public final Writer addWriter(final Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_NAME,
                        writer.getWriterName());
        namedParameters.addValue(WRITER_COUNTRY,
                writer.getWriterCountry());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addWriterSql,
                namedParameters, generatedKeyHolder);
        writer.setWriterId(generatedKeyHolder.getKey().intValue());
        return writer;
    }

    @Override
    public final void updateWriter(final Writer writer) {
        LOGGER.debug("updateWriter({})", writer);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_NAME,
                        writer.getWriterName());
        namedParameters.addValue(WRITER_COUNTRY, writer.getWriterCountry());
        namedParameters.addValue(WRITER_ID, writer.getWriterId());
        namedParameterJdbcTemplate.update(updateWriterSql, namedParameters);
    }

    @Override
    public final void deleteWriterById(final Integer writerId) {
        LOGGER.debug("deleteWriterById({})", writerId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_ID, writerId);
        namedParameterJdbcTemplate.update(
                deleteWriterSql, namedParameters);
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
