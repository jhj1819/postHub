package study.posthub.domain.member.service;

import study.posthub.domain.member.dto.MemberRequest;

public interface MemberService {

    void register(MemberRequest memberRequest); /* 회원가입 */
    void withdraw(String email); /* 회원탈퇴 */
    void updateMemberInfo(String email, MemberRequest memberRequest); /* 회원정보 수정 */
}
