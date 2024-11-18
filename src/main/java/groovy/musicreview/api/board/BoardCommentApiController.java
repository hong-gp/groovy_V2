package groovy.musicreview.api.board;

import groovy.musicreview.api.Result;
import groovy.musicreview.board.dto.request.BoardCommentSaveRequest;
import groovy.musicreview.board.dto.response.BoardCommentResponse;
import groovy.musicreview.board.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardCommentApiController {

    private final BoardCommentService boardCommentService;

    @PostMapping("/api/v1/boardComment/save")
    public void save(@RequestBody BoardCommentSaveRequest request) {
        boardCommentService.save(request);
    }

    @GetMapping("/api/v1/boardComments/{boardId}")
    public Result<List<BoardCommentResponse>> findAll(@PathVariable("boardId") Long boardId) {
        return boardCommentService.findAll(boardId);
    }
}
