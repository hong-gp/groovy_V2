package groovy.musicreview.board.repository;

import groovy.musicreview.board.domain.BoardComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentJpaRepository extends JpaRepository<BoardComment, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<BoardComment> findByBoardId(Long boardId);
}
