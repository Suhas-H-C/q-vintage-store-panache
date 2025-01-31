package org.shc.quarkus.panache.utils;

import org.shc.quarkus.panache.entity.Book;
import org.shc.quarkus.panache.enums.Languages;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import static org.shc.quarkus.panache.utils.TestUtils.CLOCK;

public class BookUtils {

    public static Book sriRamayanaDarshnamBook() {
        Book book = new Book();
        book.title = "Sri Ramayana Darshanam";
        book.createdDate = Instant.now(CLOCK);
        book.isbn = "1234567890";
        book.description = "A book about ramayana";
        book.nbOfPages = Integer.valueOf("500");
        book.price = BigDecimal.valueOf(750.50);
        book.language = Languages.KANNADA;
        book.publicationDate = LocalDate.now(CLOCK);
        return book;
    }

    public static Book dancersColony(){
        Book book = new Book();
        book.title = "Dancers Colony";
        book.createdDate = Instant.now(CLOCK);
        book.isbn = "1234567890";
        book.description = "A book about dancers";
        book.nbOfPages = Integer.valueOf("100");
        book.price = BigDecimal.valueOf(150.50);
        book.language = Languages.ENGLISH;
        book.publicationDate = LocalDate.now(CLOCK);
        return book;
    }
}
