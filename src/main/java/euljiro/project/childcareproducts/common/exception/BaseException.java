package euljiro.project.childcareproducts.common.exception;

import euljiro.project.childcareproducts.common.response.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private ErrorCode errorCode;

    public BaseException() {
    }


    public BaseException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
