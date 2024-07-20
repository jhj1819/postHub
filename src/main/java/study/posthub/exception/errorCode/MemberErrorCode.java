package study.posthub.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    // 사용자
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다."),
    NOT_FOUND_TOKEN(HttpStatus.BAD_REQUEST, "사용자 토큰을 찾을 수 없습니다."),
    ALREADY_EXIST_USERNAME(HttpStatus.FORBIDDEN, "이미 존재하는 아이디입니다."),
    ALREADY_EXIST_TEL(HttpStatus.FORBIDDEN, "이미 존재하는 전화번호입니다."),
    WRONG_PASSWORD(HttpStatus.FORBIDDEN, "잘못된 비밀번호입니다."),
    PASSWORD_NOT_EQUAL(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");

    // 게시판

    // 댓글

    private final HttpStatus httpStatus;
    private final String message;
}
