package groovy.musicreview.user.dto.request;

public record UserSaveDto(
        String loginId,
        String password,
        String name,
        String nickname,
        String tel,
        String email,
        String birth
) { }
