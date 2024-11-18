package groovy.musicreview.api.board;

import groovy.musicreview.api.Result;
import groovy.musicreview.board.dto.BoardSearch;
import groovy.musicreview.board.dto.request.BoardSaveRequest;
import groovy.musicreview.board.dto.request.BoardUpdateRequest;
import groovy.musicreview.board.dto.response.BoardResponse;
import groovy.musicreview.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/board/save")
    public void save(@RequestBody BoardSaveRequest request) {
        boardService.save(request);
    }

    @GetMapping("/api/v1/boards/{param}")
    public Result<List<BoardResponse>> findByTitle(@PathVariable("param") String param) {
        return boardService.findByTitle(param);
    }

    @GetMapping("/api/v1/boards")
    public Result<List<BoardResponse>> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/api/v2/boards")
    public Result<List<BoardResponse>> findAllV2(@ModelAttribute BoardSearch boardSearch) {
        return boardService.findAllV2(boardSearch);
    }

    @PostMapping("/api/v1/board/update")
    public BoardResponse update(@RequestBody BoardUpdateRequest request) {
        return boardService.update(request);
    }

    @PostMapping
    public void delete(Long boardId, Long userId) {
        boardService.delete(boardId, userId);
    }
}
