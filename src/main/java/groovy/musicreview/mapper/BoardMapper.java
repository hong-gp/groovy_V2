package groovy.musicreview.mapper;

import groovy.musicreview.board.dto.BoardSearch;
import groovy.musicreview.board.dto.response.BoardResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardResponse> searchByBoard(BoardSearch boardSearch);
}
