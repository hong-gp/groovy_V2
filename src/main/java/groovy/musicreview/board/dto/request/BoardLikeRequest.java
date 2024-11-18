package groovy.musicreview.board.dto.request;

public record BoardLikeRequest(
        Long userId,
        Long boardId
) {
}
