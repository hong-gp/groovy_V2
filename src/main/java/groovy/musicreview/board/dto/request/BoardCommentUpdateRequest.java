package groovy.musicreview.board.dto.request;

public record BoardCommentUpdateRequest (
        Long commentId,
        Long userId,
        String content
) {
}
