package euljiro.project.childcareproducts.common.exception;

import euljiro.project.childcareproducts.common.response.ErrorCode;

public class JwtExcepion extends BaseException {

    public JwtExcepion() {
        super(ErrorCode.UNAUTHORIZED);
    }

    public JwtExcepion(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }


}
