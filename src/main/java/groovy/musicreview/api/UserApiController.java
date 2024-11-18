package groovy.musicreview.api;

import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.dto.request.UserSaveDto;
import groovy.musicreview.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/user/save")
    public void save(@RequestBody UserSaveDto request) {
        userService.save(request);
    }

    @GetMapping("/api/v1/user/{loginId}")
    public User findOne(@PathVariable("loginId") String loginId) {
        return userService.findByUserId(loginId);
    }

    @GetMapping("/api/v1/users")
    public List<User> findAll() {
        return userService.findAll();
    }
}
