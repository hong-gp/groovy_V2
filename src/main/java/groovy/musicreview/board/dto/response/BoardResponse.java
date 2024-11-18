package groovy.musicreview.board.dto.response;

import groovy.musicreview.board.domain.Category;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse{

    private Category category;
    private String title;
    private String content;
    private String nickname;
    private int viewCount;
    private LocalDateTime writeDate;

    public BoardResponse(Category category, String title, String content, String nickname, int viewCount, LocalDateTime writeDate) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.viewCount = viewCount;
        this.writeDate = writeDate;
    }
}
