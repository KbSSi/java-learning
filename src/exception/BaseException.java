package exception;

/**
 * @author KangWenBin
 * @description 自定义异常的基类
 * @date 2023/8/13
 */
public class BaseException extends RuntimeException{
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    //cause是引起这个异常的异常，输出栈信息中包含cause的信息
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
