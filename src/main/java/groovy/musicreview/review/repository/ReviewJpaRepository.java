package groovy.musicreview.review.repository;

import groovy.musicreview.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {

    List<Review> findByAlbumId(String albumId);
}
