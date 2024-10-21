package euljiro.project.childcareproducts.application.user.dto;

import euljiro.project.childcareproducts.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserInfo {

    private final String userKey;

    private final String nickName;


    public User toEntity() {
        return User.builder()
                .userKey(userKey)
                .build();

    }

    @Getter
    @Builder
    @ToString
    public static class RegisterResponse {

        private String userKey;

        private String nickname;

        private String babyname;

        private String relationship;

        private String birthdate;
    }



}
