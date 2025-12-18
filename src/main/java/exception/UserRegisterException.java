package exception;

public class UserRegisterException extends RuntimeException {
    private final int code;

    public UserRegisterException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
