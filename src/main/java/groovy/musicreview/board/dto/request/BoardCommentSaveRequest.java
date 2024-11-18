package groovy.musicreview.board.dto.request;

public record BoardCommentSaveRequest(
        Long userId,
        String content,
        Long parentId,
        Long boardId
) {
}
