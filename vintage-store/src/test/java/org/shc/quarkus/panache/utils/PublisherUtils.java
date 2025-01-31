package org.shc.quarkus.panache.utils;

import org.shc.quarkus.panache.entity.Publisher;

import java.time.Instant;

import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

public class PublisherUtils {
    public static final String SS = "Simon & Schuster";
    public static final String SBH = "Sapna Book House";

    public static Publisher SS() {
        return new Publisher(SS, Instant.now(CLOCK));
    }

    public static Publisher SBH() {
        return new Publisher(SBH, Instant.now(CLOCK));
    }
}
