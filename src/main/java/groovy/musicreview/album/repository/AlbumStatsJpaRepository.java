package groovy.musicreview.album.repository;

import groovy.musicreview.album.domain.AlbumStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumStatsJpaRepository extends JpaRepository<AlbumStats, String> {

    List<AlbumStats> findAllByOrderByCountDesc();

    List<AlbumStats> findAllByOrderByRatingDesc();

    List<AlbumStats> findAllByOrderByWriteDateDesc();
}
