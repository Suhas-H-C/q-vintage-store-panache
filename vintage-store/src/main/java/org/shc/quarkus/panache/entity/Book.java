package org.shc.quarkus.panache.entity;


import jakarta.persistence.*;
import org.shc.quarkus.panache.enums.Languages;

import java.time.LocalDate;

@Entity
public class Book extends Item {
    @Column(length = 15)
    public String isbn;

    @Column(name = "nb_of_pages")
    public Integer nbOfPages;

    @Column(name = "publication_date")
    public LocalDate publicationDate;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public Languages language;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_fk")
    public Publisher publisher;

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", nbOfPages=" + nbOfPages +
                ", publicationDate=" + publicationDate +
                ", language=" + language +
                ", publisher=" + publisher +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdDate=" + createdDate +
                ", id=" + id +
                '}';
    }
}
