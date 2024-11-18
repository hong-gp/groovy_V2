package groovy.musicreview.board.domain;

import groovy.musicreview.domain.Like;
import groovy.musicreview.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("board")
public class BoardLike extends Like {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardLike(User user, LocalDateTime likeDate, Board board) {
        super(user, likeDate);
        this.board = board;
    }
}
