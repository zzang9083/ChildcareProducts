package euljiro.project.childcareproducts.application.user.dto;

import euljiro.project.childcareproducts.domain.common.Gender;
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

    @Getter
    @ToString
    public static class UserInfoRegisterResponse {

        private final String userKey;

        private final String nickname;

        private final Gender gender;


        public UserInfoRegisterResponse(User user) {
            this.userKey = user.getUserKey();
            this.nickname = user.getNickName();
            this.gender = user.getGender();
        }

    }



}
