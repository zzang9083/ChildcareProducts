package euljiro.project.childcareproducts.common.exception;


import euljiro.project.childcareproducts.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException {


    public EntityNotFoundException() {
        super(ErrorCode.COMMON_ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String message) {
        super(ErrorCode.COMMON_ENTITY_NOT_FOUND, message);
    }


}
