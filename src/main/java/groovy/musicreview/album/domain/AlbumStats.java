package groovy.musicreview.album.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlbumStats {

    @Id
    private String albumId;

    private long count;

    private double rating;

    private LocalDateTime writeDate;

    @Builder
    public AlbumStats(String albumId, double rating, LocalDateTime writeDate) {
        this.albumId = albumId;
        this.count++;
        this.rating = rating;
        this.writeDate = writeDate;
    }

    public void changeStat(double rating, LocalDateTime writeDate) {
        this.rating = ((this.rating * this.count) + rating) / ++this.count;
        this.writeDate = writeDate;
    }
}
