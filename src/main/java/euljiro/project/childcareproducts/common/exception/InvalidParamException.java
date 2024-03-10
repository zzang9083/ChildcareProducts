package euljiro.project.childcareproducts.common.exception;


import euljiro.project.childcareproducts.common.response.ErrorCode;

public class InvalidParamException extends BaseException {

    public InvalidParamException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public InvalidParamException(String errorMsg) {
        super(ErrorCode.COMMON_INVALID_PARAMETER, errorMsg);
    }
}
