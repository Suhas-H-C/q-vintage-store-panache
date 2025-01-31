package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.shc.quarkus.panache.utils.PublisherUtils.SBH;
import static org.shc.quarkus.panache.utils.PublisherUtils.SS;

@QuarkusTest
@TestTransaction
class PublisherRepositoryTest {

    @Test
    void shouldCreateAndFindAPublisher() {
        var p = SBH();
        p.persist();
        assertNotNull(p.id);
        Publisher byId = Publisher.findById(p.id);
        assertEquals(p.name, byId.name);
    }

    @Test
    void shouldReturnPublisherByName() {
        var p = SS();
        p.persist();
        var byName = Publisher.findByName(p.name);
        assertTrue(byName.isPresent());
    }

    @Test
    void shouldReturnAllPublishersByName() {
        var p = SS();
        p.persist();
        SBH().persist();
        var publishers = Publisher.findContainingName(p.name);
        assertEquals(1, publishers.size());
    }
}