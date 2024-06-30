package study.posthub.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.posthub.global.common.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}

