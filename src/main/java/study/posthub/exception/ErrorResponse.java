package study.posthub.exception;

import lombok.Builder;

@Builder
public record ErrorResponse( // ResponseEntity<ErrorResponse>, ResponseEntity<SuccessResponse>
        String code,
        String message
) { }