package groovy.musicreview.review.dto.request;

public record ReviewSaveRequest (
        String albumId,
        Long userId,
        String content,
        double rating
) {
}
