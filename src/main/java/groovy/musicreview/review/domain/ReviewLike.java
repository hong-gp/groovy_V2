package groovy.musicreview.review.domain;

import groovy.musicreview.domain.Like;
import groovy.musicreview.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewLike extends Like {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewLike(User user, LocalDateTime likeDate, Review review) {
        super(user, likeDate);
        this.review = review;
    }
}
