package study.posthub.global.security.oauth2.dto;

import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;

public record SessionMember(Long id, String email, String nickname, Authority authority) {

    public static SessionMember getInstance(Member member) {
        return new SessionMember(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getAuthority()
        );
    }

    public static SessionMember getAnonymousInstance() {
        return new SessionMember(
                null,
                null,
                "익명",
                Authority.SIGNOUT
        );
    }
}