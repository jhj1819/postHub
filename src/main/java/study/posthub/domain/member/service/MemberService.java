package study.posthub.domain.member.service;

import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.entity.Member;
import study.posthub.exception.custom.MemberException;

public interface MemberService {

    Long register(MemberRequest memberRequest) throws MemberException; /* 회원가입 */
    void withdraw(String email); /* 회원탈퇴 */
    void updateMemberInfo(String email, MemberRequest memberRequest) throws Exception; /* 회원정보 수정 */
    Member getOne(Long memberId); /* 회원 단건 조회 */
}
