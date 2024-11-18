package groovy.musicreview.api.review;

import groovy.musicreview.review.domain.Review;
import groovy.musicreview.review.dto.request.ReviewSaveRequest;
import groovy.musicreview.review.dto.response.ReviewResponse;
import groovy.musicreview.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("/api/v1/review/save")
    public void save(@RequestBody ReviewSaveRequest request) {
        reviewService.save(request);
    }

    @GetMapping("/api/v1/review")
    public List<ReviewResponse> findAll(String albumId) {
        return reviewService.findAll(albumId);
    }
}
