package groovy.musicreview.api;

import groovy.musicreview.board.domain.Board;
import groovy.musicreview.board.domain.Category;
import groovy.musicreview.board.repository.BoardJpaRepository;
import groovy.musicreview.review.domain.Review;
import groovy.musicreview.review.dto.request.ReviewSaveRequest;
import groovy.musicreview.review.repository.ReviewJpaRepository;
import groovy.musicreview.review.service.ReviewService;
import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.repository.UserJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final UserJpaRepository userRepository;
    private final BoardJpaRepository boardRepository;
    private final ReviewJpaRepository reviewRepository;
    private final ReviewService reviewService;

    @PostConstruct
    public void postConstruct() {
        // 회원 등록
        User user1 = new User("hong111", "1234", "홍길동", "길동이", "01011111111", "hong@naver.com", "1999-11-11");
        User user2 = new User("kim222", "1234", "김유신", "유신장군", "01022222222", "kim@gamail.com", "1989-04-10");
        userRepository.save(user1);
        userRepository.save(user2);

        // 게시판 등록
        Board board1 = new Board(Category.MUSIC, "제목111", "내용111", user1, LocalDateTime.of(2024, 10, 21, 9, 58));
        Board board2 = new Board(Category.REVIEW, "제목222", "내용222", user2, LocalDateTime.of(2024, 1, 15, 3, 30));
        boardRepository.save(board1);
        boardRepository.save(board2);

        // 리뷰 등록
        String albumId1 = "45ozep8uHHnj5CCittuyXj";
        String albumId2 = "47xcjDSi1t6pQE2RvXKdUF";
        reviewService.save(new ReviewSaveRequest(albumId1, 1L, "노래가 좋아요", 5.0));
        reviewService.save(new ReviewSaveRequest(albumId1, 2L, "좋아요", 4.5));
        reviewService.save(new ReviewSaveRequest(albumId2, 1L, "너무 좋아요", 5.0));

    }
}
