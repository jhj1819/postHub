package study.posthub.domain.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;
import study.posthub.domain.member.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입_성공() throws Exception {
        MemberRequest memberRequest = new MemberRequest("aaa11@aa.com","aaaaa", "1234");

        memberService.register(memberRequest);
        Optional<Member> member = memberRepository.findByEmail("aaa11@aa.com");

        assertThat(member.get().getEmail()).isEqualTo("aaa11@aa.com");
        assertThat(member.get().getPassword()).isEqualTo("aaaaa");
    }

    @Test
    void 회원가입_실패() throws Exception {
        MemberRequest memberRequest = new MemberRequest("aaa11@aa.com","aaa", "1234");

        assertThatThrownBy(() -> memberService.register(memberRequest));
    }

    @Test
    void 회원탈퇴_성공() throws Exception {
        MemberRequest memberRequest = new MemberRequest("aaa11@aa.com","aaaaa", "1234");

        memberService.register(memberRequest);
        memberService.withdraw("aaa11@aa.com");

        Optional<Member> member = memberRepository.findByEmail("aaa11@aa.com");

        assertThat(member.get().getDelYN()).isEqualTo(1L);
    }

    @Test
    void 회원정보수정_성공() throws Exception {
        MemberRequest memberRequest = new MemberRequest("aaa11@aa.com","aaaaa", "1234");
        MemberRequest updateMemberRequest = new MemberRequest("aaa11@aa.com","bbbbb", "1234");

        memberService.register(memberRequest);
        memberService.updateMemberInfo("aaa11@aa.com", updateMemberRequest);

        Optional<Member> member = memberRepository.findByEmail("aaa11@aa.com");

        assertThat(member.get().getPassword()).isEqualTo("bbbbb");
    }

    @Test
    void 회원정보수정_실패() throws Exception {
        MemberRequest memberRequest = new MemberRequest("aaa11@aa.com","aaaaa", "1234");
        MemberRequest updateMemberRequest = new MemberRequest("aaa11@aa.com", null , "1234");

        memberService.register(memberRequest);

        assertThatThrownBy(() -> memberService.updateMemberInfo("aaa11@aa.com", updateMemberRequest));

    }
}