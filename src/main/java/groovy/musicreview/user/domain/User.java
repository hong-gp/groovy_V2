package groovy.musicreview.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String loginId;

    private String password;
    private String name;
    private String nickname;
    private String tel;
    private String email;
    private String birth;

    @Builder
    public User(String loginId, String password, String name, String nickname, String tel, String email, String birth) {
        setLoginId(loginId);
        setPassword(password);
        setName(name);
        setNickname(nickname);
        setTel(tel);
        setEmail(email);
        setBirth(birth);
    }

    private void setLoginId(String loginId) {
        if (loginId == null || loginId.equals("")) {
            throw new IllegalArgumentException("아이디를 입력해야합니다.");
        }
        this.loginId = loginId;
    }

    private void setPassword(String password) {
        if (password == null || password.equals("")) {
            throw new IllegalArgumentException("비밀번호를 입력해야합니다.");
        }
        this.password = password;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("이름을 입력해야합니다.");
        }
        this.name = name;
    }

    private void setNickname(String nickname) {
        if (nickname == null || nickname.equals("")) {
            throw new IllegalArgumentException("별명을 입력해야합니다.");
        }
        this.nickname = nickname;
    }

    private void setTel(String tel) {
        if (tel == null || tel.equals("")) {
            throw new IllegalArgumentException("전화번호를 입력해야합니다.");
        }
        this.tel = tel;
    }

    private void setEmail(String email) {
        if (email == null || email.equals("")) {
            throw new IllegalArgumentException("이메일을 입력해야합니다.");
        }
        this.email = email;
    }

    private void setBirth(String birth) {
        if (birth == null || birth.equals("")) {
            throw new IllegalArgumentException("생년월일을 입력해야합니다.");
        }
        this.birth = birth;
    }
}
