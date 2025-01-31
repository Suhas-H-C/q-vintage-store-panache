package org.shc.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.jdbc.pojo.Artist;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.shc.quarkus.panache.utils.ArtistUtils.*;

@QuarkusTest
@TestTransaction
class ArtistRepositoryTest {

    private final ArtistRepository repository = new ArtistRepository();

    @Test
    void shouldCreateAnArtistAndReturnWhenIdIsPassed() {
        Artist artist = kuvempu();
        repository.persist(artist);
        assertNotNull(artist.getId());
        Artist responseArtist = repository.findById(artist.getId());
        assertEquals(artist.getName(), responseArtist.getName());
    }

    @Test
    void shouldReturnAllArtistByName() {
        repository.persist(sadhuKokilaComedy());
        repository.persist(sadhuKokilaMusic());
        List<Artist> allMatchingArtist = repository.findByNameSorted(SADHU_KOKILA);
        assertEquals(2, allMatchingArtist.size());
        assertEquals(SADHU_KOKILA, allMatchingArtist.get(0).getName());
    }
}