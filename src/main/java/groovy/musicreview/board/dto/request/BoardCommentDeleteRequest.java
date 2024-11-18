package groovy.musicreview.board.dto.request;

public record BoardCommentDeleteRequest (
        Long commentId,
        Long userId
) {
}
