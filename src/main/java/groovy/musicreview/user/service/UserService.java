package groovy.musicreview.user.service;

import groovy.musicreview.user.domain.User;
import groovy.musicreview.user.dto.request.UserSaveDto;
import groovy.musicreview.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserJpaRepository userRepository;

    public void save(UserSaveDto request) {
        User user = User.builder()
                .loginId(request.loginId())
                .password(request.password())
                .name(request.name())
                .nickname(request.nickname())
                .tel(request.tel())
                .email(request.email())
                .birth(request.birth())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userRepository.findByLoginId(userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("아이디가 존재하지 않는다.");
                });
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
