package groovy.musicreview.api.spotify;

import groovy.musicreview.api.Result;
import groovy.musicreview.review.dto.response.AlbumResponse;
import groovy.musicreview.spotify.dto.AlbumListOption;
import groovy.musicreview.spotify.service.SpotifyAlbumService;
import groovy.musicreview.spotify.service.SpotifyAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotifyApiController {

    private final SpotifyAuthService authService;
    private final SpotifyAlbumService albumService;

    @GetMapping("/api/spotify/auth")
    public String getAuthService() {
        return authService.getAccessToken();
    }

    @GetMapping("/api/spotify/albums")
    public Result<List<AlbumResponse>> getAlbums(@RequestParam AlbumListOption option) {
        return albumService.findAll(option);
    }
}
