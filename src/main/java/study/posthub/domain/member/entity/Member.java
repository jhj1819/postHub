package study.posthub.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.posthub.global.common.BaseTimeEntity;
import study.posthub.global.security.oauth2.dto.OAuth2Response;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = {"email", "password", "nickname", "authority", "delYN"})
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private Long delYN;

    public static Member getInstance(OAuth2Response oAuth2Response) {
        return Member.builder()
                .email(oAuth2Response.getEmail())
                .nickname(oAuth2Response.getName())
                .authority(Authority.USER)
                .build();
    }

    /* 비밀번호 암호화 */
    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    /* 회원 정보 수정 */
    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }

    public void updateByRegister(OAuth2Response oAuth2Response) {
        this.email = oAuth2Response.getEmail();
        this.nickname = oAuth2Response.getName();
    }

    public void delete() {
        this.delYN = 1L;
    }
}

