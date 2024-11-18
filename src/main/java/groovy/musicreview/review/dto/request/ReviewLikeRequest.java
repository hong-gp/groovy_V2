package groovy.musicreview.review.dto.request;

public record ReviewLikeRequest (
        Long userId,
        Long reviewId
) {
}
