package study.posthub.global.security.oauth2;

import lombok.Getter;
import lombok.ToString;
import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;

import java.io.Serializable;

@Getter
@ToString
public class SessionMember {

    private Long id;
    private String email;
    private String nickname;
    private Authority authority;

    public SessionMember(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.authority = member.getAuthority();
    }
}
