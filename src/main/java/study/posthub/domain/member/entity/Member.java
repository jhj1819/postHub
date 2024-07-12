package study.posthub.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import study.posthub.global.common.BaseTimeEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Board board;

    private String email;
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private Long delYN;

    /* 회원 정보 수정 */
    public void update(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
    }

    public void delete() {
        this.delYN = 1L;
    }
}

