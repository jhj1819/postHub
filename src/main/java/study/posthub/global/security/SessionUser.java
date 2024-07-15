package study.posthub.global.security;

import lombok.Getter;
import study.posthub.domain.member.entity.Member;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String email;
    private String nickname;

    public SessionUser(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
