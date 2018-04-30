package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;
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
     * Named parameter JDBC template.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //constant fields
    
    private static final String PUBLICATION_ID = "publication_id";
    
    private static final String PUBLICATION_NAME = "publication_name";
    
    private static final String WRITER_ID = "writer_id";
    
    private static final String PUBLICATION_DATE = "publication_date";
    
    private static final String PUBLICATION_NUM_OF_PAGES = "publication_num_of_pages";

    private static final String PUBLICATION_DESCRIPTION = "publication_description";
    

    /**
     * SQL Select-All String.
     */
    @Value("${publication.select}")
    private String GET_PUBLICATIONS_SQL;

    /**
     * SQL Select-By-Id String.
     */
    @Value("${publication.selectById}")
    private String GET_PUBLICATIONS_BY_ID_SQL;

    /**
     * SQL Insert String.
     */
    @Value("${publication.insert}")
    private String ADD_PUBLICATION_SQL;

    /**
     * Sql Update String.
     */
    @Value("${publication.update}")
    private String UPDATE_PUBLICATION_SQL;

    /**
     * Sql Delete String.
     */
    @Value("${publication.delete}")
    private String DELETE_PUBLICATION_SQL;

    /**
     * Setter.
     * @param namedParameterJdbcTemplate
     */
    public void setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Collection<Publication> getPublications() {
        Collection<Publication> publications =
                namedParameterJdbcTemplate.
                        query(GET_PUBLICATIONS_SQL,
                        new PublicationRowMapper());
        return publications;
    }

    @Override
    public final Publication getPublicationById(
            final Integer publicationId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(
                        PUBLICATION_ID, publicationId);
        Publication publication = namedParameterJdbcTemplate.
                queryForObject(GET_PUBLICATIONS_BY_ID_SQL, namedParameters,
                        BeanPropertyRowMapper.newInstance(Publication.class));
        return publication;
    }

    @Override
    public final Publication addPublication(
            final Publication publication) {
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource();
        namedParameters.addValue(
                PUBLICATION_NAME, publication.getPublicationName());
        namedParameters.addValue(
                WRITER_ID, publication.getWriterId());
        namedParameters.addValue(
                PUBLICATION_DATE, publication.getPublicationDate());
        namedParameters.addValue(
                PUBLICATION_NUM_OF_PAGES,
                        publication.getPublicationNumOfPages());
        namedParameters.addValue(
                PUBLICATION_DESCRIPTION,
                        publication.getPublicationDescription());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(
                ADD_PUBLICATION_SQL, namedParameters, generatedKeyHolder);
        publication.setPublicationId(
                generatedKeyHolder.getKey().intValue());
        return publication;
    }

    @Override
    public final void updatePublication(final Publication publication) {
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource();
        namedParameters.addValue(
                PUBLICATION_ID, publication.getPublicationId());
        namedParameters.addValue(
                PUBLICATION_NAME, publication.getPublicationName());
        namedParameters.addValue(
                WRITER_ID, publication.getWriterId());
        namedParameters.addValue(
                PUBLICATION_DATE, publication.getPublicationDate());
        namedParameters.addValue(
                PUBLICATION_NUM_OF_PAGES,
                        publication.getPublicationNumOfPages());
        namedParameters.addValue(
                PUBLICATION_DESCRIPTION,
                        publication.getPublicationDescription());
        namedParameterJdbcTemplate.update(
                UPDATE_PUBLICATION_SQL, namedParameters);
    }

    @Override
    public final void deletePublicationById(final Integer publicationId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(PUBLICATION_ID, publicationId);
        namedParameterJdbcTemplate.update(
                DELETE_PUBLICATION_SQL, namedParameters);
    }

    /**
     * PublicationRowMapper  - for creating models from resultSet.
     */
    private class PublicationRowMapper implements RowMapper<Publication> {

        @Override
        public final Publication mapRow(
                final ResultSet resultSet, final int i) throws SQLException {
            Publication publication = new Publication();
            publication.setPublicationId(resultSet.getInt(PUBLICATION_ID));
            publication.setPublicationName(resultSet.getString(PUBLICATION_NAME));
            publication.setWriterId(resultSet.getInt(WRITER_ID));
            publication.setPublicationDate(resultSet.getDate(PUBLICATION_DATE));
            publication.setPublicationNumOfPages(resultSet.getInt(PUBLICATION_NUM_OF_PAGES));
            publication.setPublicationDescription(resultSet.getString(PUBLICATION_DESCRIPTION));
            return publication;
        }
    }
}
