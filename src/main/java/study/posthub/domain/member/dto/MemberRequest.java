package study.posthub.domain.member.dto;

import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;

public record MemberRequest(
        String email,
        String password,
        String nickname
) {
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .authority(Authority.USER)
                .delYN(0L)
                .build();
    }
}
