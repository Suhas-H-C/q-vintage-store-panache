package org.shc.quarkus.panache.utils;

import org.shc.quarkus.panache.entity.Track;

import java.time.Duration;
import java.time.Instant;

import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

public class TrackUtils {

    public static Track beatIt() {
        Track track = new Track();
        track.createdDate = Instant.now(CLOCK);
        track.title = "Beat it";
        track.duration = Duration.ofMinutes(4);
        return track;
    }
}
