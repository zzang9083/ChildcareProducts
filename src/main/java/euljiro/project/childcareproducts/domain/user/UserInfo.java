package euljiro.project.childcareproducts.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
public class UserInfo {

    private final Long id;

    private final String userKey;

    private final String nickName;

    private final User.Status status;



    public UserInfo(User user) {
        this.id = user.getId();
        this.userKey = user.getUserKey();
        this.nickName = user.getNickName();
        this.status = user.getStatus();
    }

    @Getter
    @ToString
    public static class LoginResponse {

        private final String userKey;

        private final String nickName;

        private final String status;

        private final String jwtToken;

        private final String refreshToken;

        public LoginResponse(User user, String jwtToken, String refreshToken) {
            this.userKey = user.getUserKey();
            this.nickName = user.getNickName();
            this.status = user.getStatus().toString();
            this.jwtToken = jwtToken;
            this.refreshToken = refreshToken;


        }
    }

    @Getter
    @Builder
    @ToString
    public static class ReissueResponse {

        private final String jwtToken;
        private final String refreshToken;

        public ReissueResponse(String jwtToken, String refreshToken) {
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
