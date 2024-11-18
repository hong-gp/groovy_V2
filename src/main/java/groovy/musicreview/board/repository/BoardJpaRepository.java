package groovy.musicreview.board.repository;

import groovy.musicreview.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    @Override
    @Query(value = "select b from Board b join fetch b.user")
    List<Board> findAll();

    @Query(value = "select b from Board b join fetch b.user u where b.title like :param")
    List<Board> findByTitle(@Param("param") String param);
}
