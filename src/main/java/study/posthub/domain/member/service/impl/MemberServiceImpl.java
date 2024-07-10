package study.posthub.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.entity.Member;
import study.posthub.domain.member.repository.MemberRepository;
import study.posthub.domain.member.service.MemberService;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(MemberRequest memberRequest) {
        Member member = memberRequest.toEntity();

        memberRepository.save(member);
    }

    @Override
    public void withdraw(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        member.delete();
    }

    @Override
    public void updateMemberInfo(String email, MemberRequest memberRequest) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        String password = memberRequest.password();
        String nickname = memberRequest.nickname();

        member.update(password, nickname);
    }
}
