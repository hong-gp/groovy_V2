package groovy.musicreview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeInterface {

    SUCCESS(0, "SUCCESS"),
    USER_NOT_FOUND(-1, "USER_NOT_FOUND"),
    ;

    private final Integer code;
    private final String message;
}
