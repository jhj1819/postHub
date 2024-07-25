package study.posthub.exception.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.posthub.exception.errorCode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class PostException extends RuntimeException {

    private final ErrorCode errorCode;
}
