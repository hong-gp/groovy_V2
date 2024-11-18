package groovy.musicreview.board.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommentResponse {

    private Long commentId;
    private Long parentId;
    private String nickname;
    private LocalDateTime writeDate;
    private String content;

    public BoardCommentResponse(Long commentId, Long parentId, String nickname, LocalDateTime writeDate, String content) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.nickname = nickname;
        this.writeDate = writeDate;
        this.content = content;
    }
}
