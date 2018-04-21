package com.epam.brest.course.dao;

import com.epam.brest.course.model.Publication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class PublicationDaoImpl implements PublicationDao {

    JdbcTemplate jdbcTemplate;

    private final String GET_PUBLICATIONS_SQL = "SELECT publication_id, publication_name, " +
            "writer_id, publication_date, publication_num_of_pages, publication_description " +
            "FROM publication";

    public PublicationDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Publication> getPublications() {
        Collection<Publication> publications = jdbcTemplate.query(GET_PUBLICATIONS_SQL, new PublicationRowMapper());
        return publications;
    }

    @Override
    public Publication getPublicationById(Integer publicationId) {
        return null;
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
