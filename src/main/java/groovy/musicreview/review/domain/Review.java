package groovy.musicreview.review.domain;

import groovy.musicreview.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String albumId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private LocalDateTime writeDate;

    private double rating;

    @Builder
    public Review(String albumId, User user, String content, LocalDateTime writeDate, double rating) {
        this.albumId = albumId;
        this.user = user;
        this.content = content;
        this.writeDate = writeDate;
        this.rating = rating;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
