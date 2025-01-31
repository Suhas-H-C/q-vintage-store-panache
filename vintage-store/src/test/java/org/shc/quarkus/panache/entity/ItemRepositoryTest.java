package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shc.quarkus.panache.utils.ArtistUtils.michealJackson;
import static org.shc.quarkus.panache.utils.CDUtils.jazz;
import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

@QuarkusTest
@TestTransaction
class ItemRepositoryTest {

    @Test
    void shouldCreateAnItemAndReturnIt() {
        Item item = jazz();
        item.artist = michealJackson();
        item.createdDate = Instant.now(CLOCK);
        item.persist();
        Item cd = Item.findById(item.id);
        assertThat(cd)
                .usingRecursiveAssertion()
                .isEqualTo(item);
    }
}