package groovy.musicreview.board.service;

import groovy.musicreview.board.domain.Board;
import groovy.musicreview.board.domain.BoardLike;
import groovy.musicreview.board.dto.request.BoardLikeRequest;
import groovy.musicreview.board.repository.BoardJpaRepository;
import groovy.musicreview.board.repository.BoardLikeJpaRepository;
import groovy.musicreview.exception.ErrorCode;
import groovy.musicreview.exception.UserException;
import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardLikeService {

    private final BoardLikeJpaRepository boardLikeRepository;
    private final BoardJpaRepository boardRepository;
    private final UserJpaRepository userRepository;

    public void like(BoardLikeRequest request) {
        if (request.userId() == null) {
            throw new IllegalArgumentException("로그인을 해야합니다.");
        }

        boardLikeRepository.findByBoardIdAndUserId(request.boardId(), request.userId())
                .ifPresentOrElse(
                        boardLikeRepository::delete,
                        () -> save(request)
                );
    }

    private void save(BoardLikeRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> {
                    throw new UserException(ErrorCode.USER_NOT_FOUND, "user not found");
                });

        Board board = boardRepository.findById(request.boardId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("board not found");
                });

        if (board.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 작성한 글에는 좋아요를 누를 수 없다.");
        }

        BoardLike like = BoardLike.builder()
                .user(user)
                .likeDate(LocalDateTime.now())
                .board(board)
                .build();

        boardLikeRepository.save(like);
    }
}
