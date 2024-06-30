package study.posthub.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.posthub.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
