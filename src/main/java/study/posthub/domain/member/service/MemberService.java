package study.posthub.domain.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.entity.Member;

import java.io.IOException;

public interface MemberService {

    void register(MemberRequest memberRequest) throws Exception; /* 회원가입 */
    void withdraw(String email); /* 회원탈퇴 */
    void updateMemberInfo(String email, MemberRequest memberRequest) throws Exception; /* 회원정보 수정 */
    Member getOne(Long memberId); /* 회원 단건 조회 */
}
