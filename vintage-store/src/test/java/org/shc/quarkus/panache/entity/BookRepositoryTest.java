package org.shc.quarkus.panache.entity;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.shc.quarkus.panache.utils.ArtistUtils.kuvempu;
import static org.shc.quarkus.panache.utils.BookUtils.sriRamayanaDarshnamBook;
import static org.shc.quarkus.panache.utils.PublisherUtils.SBH;

@QuarkusTest
@TestTransaction
class BookRepositoryTest {

    @Test
    void shouldCreateBookAndReturnWhenIdIsPassed() {
        var book = sriRamayanaDarshnamBook();
        book.artist = kuvempu();
        book.publisher = SBH();
        book.persist();

        var sriRamayanaDarshanam = Book.findById(book.id);
        assertNotNull(book.id);
        assertThat(sriRamayanaDarshanam)
                .usingRecursiveAssertion()
                .isEqualTo(book);
    }
}