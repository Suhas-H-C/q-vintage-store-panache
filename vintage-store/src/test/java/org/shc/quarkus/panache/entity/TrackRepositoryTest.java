package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.shc.quarkus.panache.utils.CDUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shc.quarkus.panache.utils.CDUtils.*;
import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

@QuarkusTest
@TestTransaction
class TrackRepositoryTest {

    @Test
    void shouldCreateTrackAndReturnItWhenIdIsPassed() {
        Track track = new Track();
        track.title = "Trance";
        track.duration = Duration.ofMinutes(3);
        track.cd = electronic();
        track.createdDate = Instant.now(CLOCK);
        track.persist();

        Track trackById = Track.findById(track.id);
        assertThat(trackById)
                .usingRecursiveAssertion()
                .isEqualTo(track);
    }

    @Test
    void shouldGetAllTracksByDurationsSorted(){
        CD blues = blues();
        CD classical = classical();
        CD electronic = electronic();

        Track t1 = new Track();
        t1.title = "Sunday Blues";
        t1.duration = Duration.ofMinutes(2);
        t1.cd = blues;
        t1.createdDate = Instant.now(CLOCK);

        Track t2 = new Track();
        t2.title = "Classical";
        t2.duration = Duration.ofMinutes(1);
        t2.cd = classical;
        t2.createdDate = Instant.now(CLOCK);

        Track t3 = new Track();
        t3.title = "Trance";
        t3.duration = Duration.ofMinutes(2);
        t3.cd = electronic;
        t3.createdDate = Instant.now(CLOCK);

        t1.persist();
        t2.persist();
        t3.persist();
        blues.persist();
        classical.persist();
        electronic.persist();

        List<Track> allTracksByDuration = Track.getAllTracksByDuration(Duration.ofMinutes(2));
        assertThat(allTracksByDuration)
                .hasSize(2)
                .containsExactly(t3, t1);
    }
}