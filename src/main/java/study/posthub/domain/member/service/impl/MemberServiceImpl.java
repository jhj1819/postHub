package study.posthub.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.entity.Member;
import study.posthub.domain.member.repository.MemberRepository;
import study.posthub.domain.member.service.MemberService;
import study.posthub.exception.custom.MemberException;
import study.posthub.exception.errorCode.ErrorCode;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(MemberRequest memberRequest) throws MemberException {
        Member member = memberRequest.toEntity();

        // 비밀번호 암호화
        member.encodePassword(passwordEncoder);

        return memberRepository.save(member).getId();
    }

    @Override
    public void withdraw(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_MEMBER));

        member.delete();
    }

    @Override
    public void updateMemberInfo(String email, MemberRequest memberRequest) throws Exception {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_MEMBER));

        String password = memberRequest.password();
        String nickname = memberRequest.nickname();

        member.update(password, nickname);
    }

    @Override
    public Member getOne(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_MEMBER));
    }
}
