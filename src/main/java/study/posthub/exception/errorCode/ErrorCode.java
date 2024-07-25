package study.posthub.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 사용자 */
    ALREADY_EXIST_MEMBER(HttpStatus.BAD_GATEWAY, "이미 존재하는 사용자입니다."),
    NOT_FOUND_MEMBER(HttpStatus.BAD_GATEWAY, "사용자를 찾을 수 없습니다."),
    MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    /* 게시판 & 공지 */
    NOT_FOUND_TITLE(HttpStatus.NO_CONTENT, "제목을 입력하세요."),
    NOT_FOUND_CONTENT(HttpStatus.NO_CONTENT, "내용을 입력하세요."),
    TITLE_MAX_NUMBER(HttpStatus.BAD_REQUEST, "최대 글자 수를 초과했습니다."),
    NOT_FOUND_POST(HttpStatus.BAD_GATEWAY, "게시물을 찾을 수 없습니다."),
    ALREADY_DELETED(HttpStatus.BAD_GATEWAY, "이미 삭제된 게시물입니다."),

    /* 좋아요 */
    ALREADY_LIKED(HttpStatus.BAD_REQUEST, "이미 좋아요를 눌렀습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}