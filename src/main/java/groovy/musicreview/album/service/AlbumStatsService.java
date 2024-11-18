package groovy.musicreview.album.service;

import groovy.musicreview.album.domain.AlbumStats;
import groovy.musicreview.album.repository.AlbumStatsJpaRepository;
import groovy.musicreview.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumStatsService {

    private final AlbumStatsJpaRepository albumStatsRepository;

    public void setStats(Review review) {
        albumStatsRepository.findById(review.getAlbumId())
                .ifPresentOrElse(
                        albumStats -> albumStats.changeStat(review.getRating(), review.getWriteDate()),
                        () -> save(review.getAlbumId(), review.getRating(), review.getWriteDate())
                );

    }

    private void save(String albumId, double rating, LocalDateTime writeDate) {
        AlbumStats stats = AlbumStats.builder()
                .albumId(albumId)
                .rating(rating)
                .writeDate(writeDate)
                .build();

        albumStatsRepository.save(stats);
    }
}
