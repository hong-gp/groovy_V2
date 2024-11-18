package groovy.musicreview.user.controller;

import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.dto.request.LoginForm;
import groovy.musicreview.user.dto.request.UserSaveDto;
import groovy.musicreview.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("register")
    public String register(@RequestBody UserSaveDto request) {
        userService.save(request);
        return "login";
    }

    @GetMapping("login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginForm form, HttpServletResponse response) {

        // TODO 로그인

        Cookie cookie = new Cookie("userId", form.userId());
        response.addCookie(cookie);

        return "";
    }
}
