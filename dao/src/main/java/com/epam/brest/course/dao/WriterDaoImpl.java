package com.epam.brest.course.dao;

import com.epam.brest.course.dto.WriterDTO;
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

    private static final String NUMBER_OF_PUBLICATIONS =
            "number_of_publications";

    /**
     * SQL Select-all String.
     */
    @Value("${writer.select}")
    private String getWritersSql;

    /**
     * SQL Select-all-DTOs String.
     */
    @Value("${writer.selectDTOs}")
    private String getWriterDTOsSql;

    /**
     * SQL Select-By-Id String.
     */
    @Value("${writer.selectById}")
    private String getWriterByIdSql;

    /**
     * SQL Select-DTO-By-Id String.
     */
    @Value("${writer.selectDTOById}")
    private String getWriterDTOByIdSql;

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
    public final Collection<WriterDTO> getWriterDTOs() {
        LOGGER.debug("getWriterDTOs()");
        Collection<WriterDTO> writers =
                namedParameterJdbcTemplate.
                        query(getWriterDTOsSql, new WriterDTORowMapper());
        return writers;
    }

    @Override
    public final Writer getWriterById(final Integer writerId) {
        LOGGER.debug("getWriterById({})", writerId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_ID, writerId);
        Writer writer = namedParameterJdbcTemplate.queryForObject(
                getWriterByIdSql, namedParameters, new WriterRowMapper()
        );
        return writer;
    }

    @Override
    public final WriterDTO getWriterDTOById(final Integer writerId) {
        LOGGER.debug("getWriterDTOById({})", writerId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_ID, writerId);
        WriterDTO writerDTO = namedParameterJdbcTemplate.queryForObject(
                getWriterDTOByIdSql, namedParameters, new WriterDTORowMapper()
        );
        return writerDTO;
    }

    @Override
    public final Writer addWriter(final Writer writer) {
        LOGGER.debug("addWriter({})", writer);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_NAME,
                        writer.getName());
        namedParameters.addValue(WRITER_COUNTRY,
                writer.getCountry());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addWriterSql,
                namedParameters, generatedKeyHolder);
        writer.setId(generatedKeyHolder.getKey().intValue());
        return writer;
    }

    @Override
    public final void updateWriter(final Writer writer) {
        LOGGER.debug("updateWriter({})", writer);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource(WRITER_NAME,
                        writer.getName());
        namedParameters.addValue(WRITER_COUNTRY, writer.getCountry());
        namedParameters.addValue(WRITER_ID, writer.getId());
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
            writer.setId(resultSet.getInt(WRITER_ID));
            writer.setName(resultSet.getString(WRITER_NAME));
            writer.setCountry(resultSet.getString(WRITER_COUNTRY));
            return writer;
        }
    }

    /**
     * WriterDTORowMapper  - for creating models from resultSet.
     */
    private class WriterDTORowMapper implements RowMapper<WriterDTO> {

        @Override
        public final WriterDTO mapRow(
                final ResultSet resultSet, final int i)
                throws SQLException {
            WriterDTO writerDTO = new WriterDTO();
            writerDTO.setId(resultSet.getInt(WRITER_ID));
            writerDTO.setName(resultSet.getString(WRITER_NAME));
            writerDTO.setCountry(resultSet.getString(WRITER_COUNTRY));
            writerDTO.setNumberOfPublications(resultSet.getInt(
                    NUMBER_OF_PUBLICATIONS));
            return writerDTO;
        }
    }
}
