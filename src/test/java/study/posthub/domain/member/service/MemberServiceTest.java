package study.posthub.domain.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Test
    void test() throws Exception {
        String a = "홍길동";
        String b = "홍길동";
        String c = "홍길동";

        assertThat(a).isNotNull();
        assertThat(b).isNotNull();
        assertThat(c).isNotNull();

        assertThat(a).isEqualTo(b);
        assertThat(c).isEqualTo(a);
    }
}