package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.shc.quarkus.panache.utils.CDUtils.pop;
import static org.shc.quarkus.panache.utils.CDUtils.rock;
import static org.shc.quarkus.panache.utils.TrackUtils.beatIt;

@QuarkusTest
@TestTransaction
class CDRepositoryTest {

    @Test
    void shouldCreateCDAndReturnWhenIdIsPassed() {
        CD cd = rock();
        Track track = beatIt();
        track.cd = cd;
        track.persist();
        cd.addTrack(track);
        cd.persist();

        CD cdById = CD.findById(cd.id);
        assertNotNull(cd.id);
        assertThat(cdById)
                .usingRecursiveAssertion()
                .isEqualTo(cd);
        assertEquals(1, CD.count());
        assertEquals(1, CD.listAll().size());
        cd.delete();
        assertEquals(0, CD.count());
        assertEquals(0, CD.listAll().size());
        assertEquals(0, Track.count());
        assertEquals(0, Track.listAll().size());
    }

    @Test
    void shouldReturnAllCDMatchingGenre() {
        rock().persist();
        pop().persist();
        assertEquals(1, CD.findContainGenre("Rock").size());
    }
}