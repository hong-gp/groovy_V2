package groovy.musicreview.review.service;

import groovy.musicreview.album.domain.AlbumStats;
import groovy.musicreview.album.service.AlbumStatsService;
import groovy.musicreview.exception.ErrorCode;
import groovy.musicreview.exception.UserException;
import groovy.musicreview.review.domain.Review;
import groovy.musicreview.review.dto.request.ReviewDeleteRequest;
import groovy.musicreview.review.dto.request.ReviewSaveRequest;
import groovy.musicreview.review.dto.request.ReviewUpdateRequest;
import groovy.musicreview.review.dto.response.ReviewResponse;
import groovy.musicreview.review.repository.ReviewJpaRepository;
import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewJpaRepository reviewRepository;
    private final UserJpaRepository userRepository;
    private final AlbumStatsService albumStatsService;

    public void save(ReviewSaveRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new UserException(ErrorCode.USER_NOT_FOUND, "user not found");
                });

        Review review = Review.builder()
                .albumId(request.albumId())
                .user(user)
                .content(request.content())
                .writeDate(LocalDateTime.now())
                .rating(request.rating())
                .build();

        albumStatsService.setStats(review);
        reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(String albumId) {
        List<Review> reviews = reviewRepository.findByAlbumId(albumId);

        return reviews.stream()
                .map(r -> new ReviewResponse(r.getUser().getNickname(), r.getWriteDate(), r.getRating(), r.getContent()))
                .toList();
    }

    public void update(ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(request.reviewId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("review not found");
                });

        if (review.getUser().getId().equals(request.userId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        review.changeContent(request.content());
    }

    public void delete(ReviewDeleteRequest request) {
        Review review = reviewRepository.findById(request.reviewId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("review not found");
                });

        if (review.getUser().getId().equals(request.userId())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        reviewRepository.delete(review);
    }
}
