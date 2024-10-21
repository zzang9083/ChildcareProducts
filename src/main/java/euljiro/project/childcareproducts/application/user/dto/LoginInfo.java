package euljiro.project.childcareproducts.application.user.dto;

import euljiro.project.childcareproducts.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
public class LoginInfo {

    private final Long id;

    private final String userKey;

    private final String nickName;

    private final User.Status status;



    public LoginInfo(User user) {
        this.id = user.getId();
        this.userKey = user.getUserKey();
        this.nickName = user.getNickName();
        this.status = user.getStatus();
    }

    @Getter
    @ToString
    public static class LoginResponse {

        private final String userKey;
        private final String status;

        private final String token;

        private final String refreshToken;

        public LoginResponse(User user, String jwtToken, String refreshToken) {
            this.userKey = user.getUserKey();
            this.status = user.getStatus().toString();
            this.token = jwtToken;
            this.refreshToken = refreshToken;


        }
    }

    @Getter
    @Builder
    @ToString
    public static class ReissueResponse {

        private final String userKey;

        private final String status;

        private final String jwtToken;
        private final String refreshToken;

        public ReissueResponse(User user, String jwtToken, String refreshToken) {
            this.userKey = user.getUserKey();
            this.status  = user.getStatus().toString();
            this.jwtToken = jwtToken;
            this.refreshToken = refreshToken;


        }
    }

    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long id;

        private final String userToken;

        private final String nickName;

        private final String relationship;

        private final String status;
    }
}
