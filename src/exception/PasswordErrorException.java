package exception;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/13
 */
public class PasswordErrorException extends BaseException{
    public PasswordErrorException() {
    }

    public PasswordErrorException(String message) {
        super(message);
    }

    public PasswordErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
