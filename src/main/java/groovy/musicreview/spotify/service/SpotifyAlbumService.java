package groovy.musicreview.spotify.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import groovy.musicreview.album.domain.AlbumStats;
import groovy.musicreview.album.repository.AlbumStatsJpaRepository;
import groovy.musicreview.api.Result;
import groovy.musicreview.review.dto.response.AlbumResponse;
import groovy.musicreview.review.repository.ReviewJpaRepository;
import groovy.musicreview.spotify.dto.AlbumListOption;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SpotifyAlbumService {

    private final SpotifyAuthService authService;
    private final ReviewJpaRepository reviewRepository;
    private final AlbumStatsJpaRepository albumStatsRepository;

    public Result<List<AlbumResponse>> findAll(AlbumListOption option) {
        if (option == AlbumListOption.RATING) {
            return orderByRating();
        } else if (option == AlbumListOption.REVIEW_COUNT) {
            return orderByReview();
        } else {
            return newRelease();
        }
    }

    /**
     * 최신순 조회
     */
    private Result<List<AlbumResponse>> newRelease() {
        List<AlbumStats> responses = albumStatsRepository.findAllByOrderByWriteDateDesc();

        return getResult(responses);
    }

    /**
     * 리뷰순 조회
     */
    private Result<List<AlbumResponse>> orderByReview() {
        List<AlbumStats> responses = albumStatsRepository.findAllByOrderByCountDesc();

        return getResult(responses);

    }

    /**
     * 평점순 조회
     */
    private Result<List<AlbumResponse>> orderByRating() {
        List<AlbumStats> responses = albumStatsRepository.findAllByOrderByRatingDesc();

        return getResult(responses);
    }

    /**
     * 편의 메서드
     */
    private Result<List<AlbumResponse>> getResult(List<AlbumStats> responses) {
        List<AlbumResponse> albums = new ArrayList<>();
        try {
            for (AlbumStats response : responses) {
                Album album = getSpotifyApi().getAlbum(response.getAlbumId()).build().execute();

                albums.add(
                        new AlbumResponse(
                                album.getImages()[0].getUrl(),
                                album.getName(),
                                Arrays.stream(album.getArtists()).map(ArtistSimplified::getName).toList(),
                                album.getReleaseDate(),
                                response.getCount(),
                                response.getRating()
                        )
                );
            }
            return new Result<>(albums.size(), albums);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private SpotifyApi getSpotifyApi() {
        return new SpotifyApi.Builder()
                .setAccessToken(authService.getAccessToken())
                .build();
    }
}
