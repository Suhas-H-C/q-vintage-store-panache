package org.shc.quarkus.jdbc.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.jdbc.pojo.Artist;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    void shouldCreateAndFindAnArtist() throws SQLException {
        Artist artist = new Artist("John Doe", "Bio");
        repository.persist(artist);
        assertNotNull(artist.getId());
        Artist responseArtist = repository.findById(artist.getId());
        assertEquals(artist.getName(), responseArtist.getName());
    }
}