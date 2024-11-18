package groovy.musicreview.review.service;

import groovy.musicreview.review.domain.Review;
import groovy.musicreview.review.domain.ReviewLike;
import groovy.musicreview.review.dto.request.ReviewLikeRequest;
import groovy.musicreview.review.repository.ReviewJpaRepository;
import groovy.musicreview.review.repository.ReviewLikeJpaRepository;
import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLikeService {

    private final ReviewLikeJpaRepository likeRepository;
    private final UserJpaRepository userRepository;
    private final ReviewJpaRepository reviewRepository;

    public void like(ReviewLikeRequest request) {
        likeRepository.findByUserIdAndReviewId(request.userId(), request.reviewId())
                .ifPresentOrElse(
                        likeRepository::delete,
                        () -> save(request)
                );
    }

    private void save(ReviewLikeRequest request) {
        Review review = reviewRepository.findById(request.reviewId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("review not found");
                });

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("user not found");
                });

        if (review.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 작성한 리뷰에는 좋아요를 누를 수 없다.");
        }

        ReviewLike like = ReviewLike.builder()
                .user(user)
                .likeDate(LocalDateTime.now())
                .review(review)
                .build();

        likeRepository.save(like);
    }
}
