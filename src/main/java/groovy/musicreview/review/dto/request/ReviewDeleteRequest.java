package groovy.musicreview.review.dto.request;

public record ReviewDeleteRequest (
        Long reviewId,
        Long userId
) {
}
