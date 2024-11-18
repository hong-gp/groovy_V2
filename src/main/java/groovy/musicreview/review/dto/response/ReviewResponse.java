package groovy.musicreview.review.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse (
        String nickname,
        LocalDateTime writeDate,
        double rating,
        String content
) {
}
