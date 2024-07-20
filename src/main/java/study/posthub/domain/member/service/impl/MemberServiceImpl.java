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
    public void register(MemberRequest memberRequest) throws Exception {
        Member member = memberRequest.toEntity();

        if (member.getPassword().length() < 5){
            throw new Exception("비밀번호가 4글자 이하입니다");
        }

        memberRepository.save(member);
    }

    @Override
    public void withdraw(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        member.delete();
    }

    @Override
    public void updateMemberInfo(String email, MemberRequest memberRequest) throws Exception {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        String password = memberRequest.password();
        String nickname = memberRequest.nickname();

        if (password.isEmpty()){
            throw new Exception("비밀번호가 null값입니다.");
        }

        if (password.length() < 5){
            throw new Exception("비밀번호가 4글자 이하입니다");
        }

        member.update(password, nickname);
    }

    @Override
    public Member getOne(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
