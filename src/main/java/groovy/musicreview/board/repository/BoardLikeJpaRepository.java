package groovy.musicreview.board.repository;

import groovy.musicreview.board.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeJpaRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findByBoardIdAndUserId(Long boardId, Long userId);
}
