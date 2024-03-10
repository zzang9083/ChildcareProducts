package euljiro.project.childcareproducts.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserCommand {

    private final String userKey;

    private final String nickName;


    public User toEntity() {
        return User.builder()
                .userKey(userKey)
                .nickName(nickName)
                .build();

    }

    @Getter
    @Builder
    @ToString
    public static class LoginRequest {
        private String accessToken;
    }

    @Getter
    @Builder
    @ToString
    public static class ReissueRequest {
        private String refreshToken;
    }


    @Getter
    @Builder
    @ToString
    public static class CreateShareCodeRequest {
        private String userToken;
    }


}
