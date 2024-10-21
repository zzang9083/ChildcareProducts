package euljiro.project.childcareproducts.application.item.dto;

import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {
        @NotEmpty(message = "userKey 는 필수값입니다")
        private String userKey;

        private String nickname;

        private String babyname;

        private String relationship;

        private String birthdate;

        public UserCommand.RegisterRequest toCommand() {
            return UserCommand.RegisterRequest.builder()
                    .userKey(userKey)
                    .nickname(nickname)
                    .babyname(babyname)
                    .relationship(relationship)
                    .birthdate(birthdate)
                    .build();
        }

    }



    @Getter
    @Builder
    @ToString
    public static class LoginResponse {

        private final String userKey;

        private final String status;

        private final String token;

        private final String refreshToken;

    }
}
