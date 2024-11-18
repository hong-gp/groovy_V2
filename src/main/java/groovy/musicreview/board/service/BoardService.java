package groovy.musicreview.board.service;

import groovy.musicreview.api.Result;
import groovy.musicreview.board.domain.Board;
import groovy.musicreview.board.dto.BoardSearch;
import groovy.musicreview.board.dto.request.BoardSaveRequest;
import groovy.musicreview.board.dto.request.BoardUpdateRequest;
import groovy.musicreview.board.dto.response.BoardResponse;
import groovy.musicreview.board.repository.BoardJpaRepository;
import groovy.musicreview.mapper.BoardMapper;
import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardJpaRepository boardRepository;
    private final UserJpaRepository userRepository;

    private final BoardMapper boardMapper;

    public void save(BoardSaveRequest request) {

        User findUser = userRepository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 유저입니다.");
                });

        Board board = Board.builder()
                .category(request.category())
                .title(request.title())
                .content(request.content())
                .user(findUser)
                .writeDate(LocalDateTime.now())
                .build();

        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Result<List<BoardResponse>> findByTitle(String param) {
        List<Board> boards = boardRepository.findByTitle("%" + param + "%");

        List<BoardResponse> collect = toBoardResponses(boards);
        return new Result<>(collect.size(), collect);
    }

    @Transactional(readOnly = true)
    public Result<List<BoardResponse>> findAll() {
        List<Board> boards = boardRepository.findAll();

        List<BoardResponse> collect = toBoardResponses(boards);

        return new Result<>(collect.size(), collect);
    }

    @Transactional(readOnly = true)
    public Result<List<BoardResponse>> findAllV2(BoardSearch boardSearch) {
        List<BoardResponse> collect = boardMapper.searchByBoard(boardSearch);

        return new Result<>(collect.size(), collect);
    }

    public BoardResponse update(BoardUpdateRequest request) {
        Board findBoard = boardRepository.findById(request.boardId()).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다");
        });

        if (!findBoard.getUser().getId().equals(request.userId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        findBoard.changeCategory(request.category());
        findBoard.changeTitle(request.title());
        findBoard.changeContent(request.content());

        return new BoardResponse(
                findBoard.getCategory(),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getUser().getNickname(),
                findBoard.getViewCount(),
                findBoard.getWriteDate()
        );
    }

    public void delete(Long id, Long userId) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다");
        });

        if (!findBoard.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        boardRepository.delete(findBoard);
    }

    private List<BoardResponse> toBoardResponses(List<Board> boards) {
        return boards.stream()
                .map(b -> new BoardResponse(b.getCategory(), b.getTitle(), b.getContent(), b.getUser().getNickname(), b.getViewCount(), b.getWriteDate()))
                .toList();
    }
}
