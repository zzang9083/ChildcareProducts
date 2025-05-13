package euljiro.project.childcareproducts.application.complex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class LoginCommand {

    @Getter
    @Builder
    @ToString
    public static class LoginRequest {
        private final String accessToken;
        private final String pushToken;
    }

    @Getter
    @Builder
    @ToString
    public static class ReissueRequest {
        private final String refreshToken;
        private final String firebaseToken;
    }

}
