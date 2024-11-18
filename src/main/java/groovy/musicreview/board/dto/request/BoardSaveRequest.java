package groovy.musicreview.board.dto.request;

import groovy.musicreview.board.domain.Category;

public record BoardSaveRequest(
        Category category,
        String title,
        String content,
        Long userId
) { }
