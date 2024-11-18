package groovy.musicreview.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final CodeInterface codeInterface;

    public UserException(CodeInterface ci) {
        super(ci.getMessage());
        this.codeInterface = ci;
    }

    public UserException(CodeInterface ci, String message) {
        super(ci.getMessage() + message);
        this.codeInterface = ci;
    }
}
