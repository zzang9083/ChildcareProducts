package euljiro.project.childcareproducts.api.user.sharecode.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ShareCodeDto {

    @Getter
    @Setter
    @ToString
    public static class ShareCodeRequest {
        @NotEmpty(message = "userToken 는 필수값입니다")
        private String userKey;
    }

    @Getter
    @Setter
    @ToString
    public static class ShareCodeResponse {
        private String shareCode;
    }
}
