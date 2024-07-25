package study.posthub.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;

public record MemberRequest(
        @Email(message = "이메일 형식을 맞춰 입력하세요.")
        String email,

        @NotBlank(message = "6글자 이상 입력하세요.")
        @Size(min = 6, max = 15)
        String password,

        @NotBlank(message = "알파벳, 한글, 숫자를 10글자 이하로 입력하세요.")
        @Size(min = 4, max = 10)
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
