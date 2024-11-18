package groovy.musicreview.spotify.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpotifyAuthService {

//    @Value("${spotify.client-id}")
    private static final String CLIENT_ID = "f5743c3dfe5242ad89c1b79db91973c3";

//    @Value("${spotify.client-secret}")
    private static final String CLIENT_SECRET = "482338fa4c1541ce962dfee753c1585d";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .build();

    public String getAccessToken() {
        ClientCredentialsRequest request = spotifyApi.clientCredentials().build();
        try {
            ClientCredentials clientCredentials = request.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            return spotifyApi.getAccessToken();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
