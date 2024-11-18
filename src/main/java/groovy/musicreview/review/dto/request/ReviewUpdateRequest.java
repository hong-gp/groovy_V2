package groovy.musicreview.review.dto.request;

public record ReviewUpdateRequest (
        Long reviewId,
        String content,
        Long userId
) {
}
