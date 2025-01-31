package org.shc.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.shc.quarkus.jdbc.pojo.Artist;

import java.util.List;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {

    public List<Artist> findByNameSorted(String name) {
        return list("name= ?1", Sort.by("bio"), name);
    }
}
