package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
 * Implementation of PublicationDao.
 */
public class PublicationDaoImpl implements PublicationDao {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Named parameter JDBC template.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //constant fields

    private static final String PUBLICATION_ID = "publication_id";

    private static final String PUBLICATION_NAME = "publication_name";

    private static final String WRITER_ID = "writer_id";
    
    private static final String PUBLICATION_DATE = "publication_date";
    
    private static final String PUBLICATION_NUM_OF_PAGES =
            "publication_num_of_pages";

    private static final String PUBLICATION_DESCRIPTION =
            "publication_description";

    /**
     * SQL Select-All String.
     */
    @Value("${publication.select}")
    private String getPublicationsSql;

    /**
     * SQL Select-By-Id String.
     */
    @Value("${publication.selectById}")
    private String getPublicationByIdSql;

    /**
     * SQL Insert String.
     */
    @Value("${publication.insert}")
    private String addPublicationSql;

    /**
     * Sql Update String.
     */
    @Value("${publication.update}")
    private String updatePublicationSql;

    /**
     * Sql Delete String.
     */
    @Value("${publication.delete}")
    private String deletePublicationSql;

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
    public final Collection<Publication> getPublications() {
        LOGGER.debug("getPublications()");
        Collection<Publication> publications =
                namedParameterJdbcTemplate.
                        query(getPublicationsSql,
                        new PublicationRowMapper());
        return publications;
    }

    @Override
    public final Publication getPublicationById(
            final Integer publicationId) {
        LOGGER.debug("getPublicationById({})", publicationId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(
                        PUBLICATION_ID, publicationId);
        Publication publication = namedParameterJdbcTemplate.
                queryForObject(getPublicationByIdSql, namedParameters,
                        BeanPropertyRowMapper.newInstance(Publication.class));
        return publication;
    }

    @Override
    public final Publication addPublication(
            final Publication publication) {
        LOGGER.debug("addPublication({})", publication);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource();
        namedParameters.addValue(
                PUBLICATION_NAME, publication.getName());
        namedParameters.addValue(
                WRITER_ID, publication.getWriterId());
        namedParameters.addValue(
                PUBLICATION_DATE, publication.getDate());
        namedParameters.addValue(
                PUBLICATION_NUM_OF_PAGES,
                        publication.getNumberOfPages());
        namedParameters.addValue(
                PUBLICATION_DESCRIPTION,
                        publication.getDescription());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(
                addPublicationSql, namedParameters, generatedKeyHolder);
        publication.setId(
                generatedKeyHolder.getKey().intValue());
        return publication;
    }

    @Override
    public final void updatePublication(final Publication publication) {
        LOGGER.debug("updatePublication({})", publication);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource();
        namedParameters.addValue(
                PUBLICATION_ID, publication.getId());
        namedParameters.addValue(
                PUBLICATION_NAME, publication.getName());
        namedParameters.addValue(
                WRITER_ID, publication.getWriterId());
        namedParameters.addValue(
                PUBLICATION_DATE, publication.getDate());
        namedParameters.addValue(
                PUBLICATION_NUM_OF_PAGES,
                        publication.getNumberOfPages());
        namedParameters.addValue(
                PUBLICATION_DESCRIPTION,
                        publication.getDescription());
        namedParameterJdbcTemplate.update(
                updatePublicationSql, namedParameters);
    }

    @Override
    public final void deletePublicationById(final Integer publicationId) {
        LOGGER.debug("deletePublicationById({})", publicationId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(PUBLICATION_ID, publicationId);
        namedParameterJdbcTemplate.update(
                deletePublicationSql, namedParameters);
    }

    /**
     * PublicationRowMapper  - for creating models from resultSet.
     */
    private class PublicationRowMapper implements RowMapper<Publication> {

        @Override
        public final Publication mapRow(
                final ResultSet resultSet, final int i) throws SQLException {
            Publication publication = new Publication();
            publication.setId(resultSet.getInt(PUBLICATION_ID));
            publication.setName(
                    resultSet.getString(PUBLICATION_NAME));
            publication.setWriterId(resultSet.getInt(WRITER_ID));
            publication.setDate(resultSet.getDate(PUBLICATION_DATE));
            publication.setNumberOfPages(
                    resultSet.getInt(PUBLICATION_NUM_OF_PAGES));
            publication.setDescription(
                    resultSet.getString(PUBLICATION_DESCRIPTION));
            return publication;
        }
    }
}
