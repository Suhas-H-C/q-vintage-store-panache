package org.shc.quarkus.panache.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public Duration duration;

    @JoinColumn(name = "cd_fk")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    public CD cd;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public Track() {
    }

    @Override
    public String toString() {
        return "Track{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", createdDate=" + createdDate +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id.equals(track.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static List<Track> getAllTracksByDuration(Duration duration) {
        return list("duration=?1", Sort.by("title", Sort.Direction.Descending), duration);
    }
}
