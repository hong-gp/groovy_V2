package groovy.musicreview.api.board;

import groovy.musicreview.board.dto.request.BoardLikeRequest;
import groovy.musicreview.board.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardLikeApiController {

    private final BoardLikeService boardLikeService;

    @PostMapping("/api/v1/boardLike")
    public void like(@RequestBody BoardLikeRequest request) {
        boardLikeService.like(request);
    }
}
