package groovy.musicreview.api.review;

import groovy.musicreview.review.dto.request.ReviewLikeRequest;
import groovy.musicreview.review.service.ReviewLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewLikeApiController {

    private final ReviewLikeService likeService;

    @PostMapping("/api/v1/reviewLike")
    public void like(@RequestBody ReviewLikeRequest request) {
        likeService.like(request);
    }
}
