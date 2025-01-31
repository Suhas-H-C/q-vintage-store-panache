package org.shc.quarkus.jdbc.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.shc.quarkus.jdbc.pojo.Artist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {

    @Inject
    DataSource dataSource;

    @Inject
    Logger log;

    public void persist(Artist artist) throws SQLException {
        Connection connection = dataSource.getConnection();

        // Ensure table exists
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS t_artist (
            id BIGINT PRIMARY KEY,
            name VARCHAR(255),
            bio TEXT,
            createdDate TIMESTAMP
        )
        """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
        String sql = """
                INSERT INTO t_artist (id, name, bio, createdDate)
                VALUES (?, ?, ?, ?)
                """;
        artist.setId(Math.abs(new Random().nextLong()));
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, artist.getId());
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getBio());
            statement.setTimestamp(4, Timestamp.from(artist.getCreatedDate()));
            statement.executeUpdate();
            log.info("Artist persisted with id: " + artist.getId());
        }
        connection.close();
    }

    public Artist findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = """
                SELECT id, name, bio, createdDate
                FROM t_artist
                WHERE id = ?
                """;

        Artist artist = new Artist();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                artist.setId(resultSet.getLong("id"));
                artist.setName(resultSet.getString("name"));
                artist.setBio(resultSet.getString("bio"));
                artist.setCreatedDate(resultSet.getTimestamp("createdDate").toInstant());
                log.info("Artist found with id: " + artist.getId());
                return artist;
            }
        }
        connection.close();
        return artist;
    }
}