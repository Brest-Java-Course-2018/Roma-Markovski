package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;
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

public class PublicationDaoImpl implements PublicationDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String GET_PUBLICATIONS_SQL = "SELECT publication_id, publication_name, " +
            "writer_id, publication_date, publication_num_of_pages, publication_description " +
            "FROM publication";

    private final String GET_PUBLICATIONS_BY_ID_SQL = "SELECT publication_id, publication_name, " +
            "writer_id, publication_date, publication_num_of_pages, publication_description " +
            "FROM publication WHERE publication_id = :publication_id";

    private static final String ADD_PUBLICATION_SQL = "INSERT INTO publication " +
            "(publication_name, writer_id, publication_date, publication_num_of_pages, " +
            "publication_description) VALUES (:publication_name, :writer_id, " +
            ":publication_date, :publication_num_of_pages, :publication_description)";

    public PublicationDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Collection<Publication> getPublications() {
        Collection<Publication> publications = jdbcTemplate.query(GET_PUBLICATIONS_SQL, new PublicationRowMapper());
        return publications;
    }

    @Override
    public Publication getPublicationById(Integer publicationId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("publication_id", publicationId);
        Publication publication = namedParameterJdbcTemplate.
                queryForObject(GET_PUBLICATIONS_BY_ID_SQL, namedParameters, new PublicationRowMapper());
        return publication;
    }

    @Override
    public Publication addPublication(Publication publication) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("publication_name", publication.getPublicationName());
        namedParameters.addValue("writer_id", publication.getWriterId());
        namedParameters.addValue("publication_date", publication.getPublicationDate());
        namedParameters.addValue("publication_num_of_pages", publication.getPublicationNumOfPages());
        namedParameters.addValue("publication_description", publication.getPublicationDescription());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_PUBLICATION_SQL, namedParameters, generatedKeyHolder);
        publication.setPublicationId(generatedKeyHolder.getKey().intValue());
        return publication;
    }

    private class PublicationRowMapper implements RowMapper<Publication> {

        @Override
        public Publication mapRow(ResultSet resultSet, int i) throws SQLException {
            Publication publication = new Publication();
            publication.setPublicationId(resultSet.getInt(1));
            publication.setPublicationName(resultSet.getString(2));
            publication.setWriterId(resultSet.getInt(3));
            publication.setPublicationDate(resultSet.getDate(4));
            publication.setPublicationNumOfPages(resultSet.getInt(5));
            publication.setPublicationDescription(resultSet.getString(6));
            return publication;
        }
    }
}
