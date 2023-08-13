package exception;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/13
 */
public class UserNotFoundException extends BaseException{
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
