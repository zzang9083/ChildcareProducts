package euljiro.project.childcareproducts.common.exception;

import euljiro.project.childcareproducts.common.response.ErrorCode;

public class ExternalApiException extends BaseException {


    public ExternalApiException() {
        super(ErrorCode.EXTERNAL_API_ERROR);
    }

    public ExternalApiException(String message) {
        super(ErrorCode.EXTERNAL_API_ERROR, message);
    }
}
