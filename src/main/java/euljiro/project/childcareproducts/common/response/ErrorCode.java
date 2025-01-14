package euljiro.project.childcareproducts.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    // 인증
    UNAUTHORIZED("인증되지않은 요청입니다."),
    EXPIRED_TOKEN("만료된 토큰입니다."),
    INVALID_ACCESS_TOKEN("유효하지않은 액세스 토큰입니다."),
    INVALID_REFRESH_TOKEN("유효하지않은 리프레시 토큰입니다."),
    BAD_REQUEST("잘못된 요청입니다."),

    // REDIS
    REDIS_CONNECTION_FAILURE("REDIS 연결에 오류가 발생했습니다."),
    REDIS_NOT_EXIST_SHARECODE("만료되거나 유효하지않은 공유코드입니다."),

    // 외부API
    EXTERNAL_API_ERROR("외부 API 통신중 오류가 발생했습니다.");


    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
