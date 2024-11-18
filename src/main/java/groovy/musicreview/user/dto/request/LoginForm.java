package groovy.musicreview.user.dto.request;

public record LoginForm(
        String userId,
        String password
) {
}
