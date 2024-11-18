package groovy.musicreview.board.service;

import groovy.musicreview.api.Result;
import groovy.musicreview.board.domain.Board;
import groovy.musicreview.board.domain.BoardComment;
import groovy.musicreview.board.dto.request.BoardCommentDeleteRequest;
import groovy.musicreview.board.dto.request.BoardCommentSaveRequest;
import groovy.musicreview.board.dto.request.BoardCommentUpdateRequest;
import groovy.musicreview.board.dto.response.BoardCommentResponse;
import groovy.musicreview.board.repository.BoardCommentJpaRepository;
import groovy.musicreview.board.repository.BoardJpaRepository;
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
public class BoardCommentService {

    private final UserJpaRepository userRepository;
    private final BoardJpaRepository boardRepository;
    private final BoardCommentJpaRepository boardCommentRepository;

    public void save(BoardCommentSaveRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 유저입니다.");
                });

        Board board = boardRepository.findById(request.boardId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 글입니다.");
                });

        BoardComment comment = BoardComment.builder()
                .user(user)
                .content(request.content())
                .writeDate(LocalDateTime.now())
                .board(board)
                .build();

        addChildComment(request.parentId(), comment);

        boardCommentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Result<List<BoardCommentResponse>> findAll(Long boardId) {
        List<BoardComment> comments = boardCommentRepository.findByBoardId(boardId);

        List<BoardCommentResponse> collect = comments.stream()
                .map(c -> new BoardCommentResponse(
                        c.getId(),
                        c.getParent() != null ? c.getParent().getId() : null,
                        c.getUser().getNickname(), c.getWriteDate(), c.getContent()))
                .toList();

        return new Result<>(collect.size(), collect);
    }

    public void update(BoardCommentUpdateRequest request) {
        BoardComment comment = boardCommentRepository.findById(request.commentId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("comment not found");
                });

        if (!comment.getUser().getId().equals(request.userId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        comment.changeComment(request.content());
    }

    public void delete(BoardCommentDeleteRequest request) {
        BoardComment comment = boardCommentRepository.findById(request.commentId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
                });

        if (!comment.getUser().getId().equals(request.userId())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        boardCommentRepository.delete(comment);
    }

    private void addChildComment(Long parentId, BoardComment child) {
        if (parentId != null && parentId != 0) {
            BoardComment parent = boardCommentRepository.findById(parentId)
                    .orElseThrow(() -> {
                        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
                    });
            parent.addChildComment(child);
        }
    }
}
