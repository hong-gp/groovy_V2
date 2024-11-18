package groovy.musicreview.board.dto;

import groovy.musicreview.board.domain.Category;
import lombok.Data;

@Data
public class BoardSearch {

    private Category category;
    private BoardSearchOption option;
    private String keyword;
}
