package groovy.musicreview.review.dto.response;

import java.util.List;

public record AlbumResponse (
        String images,
        String name,
        List<String> artists,
        String releaseDate,
        long reviewCount,
        double rating
) {
}
