package euljiro.project.childcareproducts.api.complex.dto;

import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class LoginDto {

//    @Getter
//    @Setter
//    @ToString
//    public static class JoinUserRequest {
//        @NotEmpty(message = "userToken 는 필수값입니다")
//        private String userToken;
//
//        @NotEmpty(message = "nickName 는 필수값입니다")
//        private String nickName;
//
//
//
//        public UserCommand toCommand() {
//            return UserCommand.builder()
//                    .userToken(userToken)
//                    .nickName(userToken)
//                    .build();
//        }
//    }

//    @Getter
//    @ToString
//    public static class JoinUserResponse {
//
//        private final long userId;
//
//        private final String userToken;
//
//        private final String nickName;
//
//        private final String relationship;
//
//        private final User.Status status;
//
//        public JoinUserResponse(UserInfo userInfo) {
//            this.userId = userInfo.getId();
//            this.userToken = userInfo.getUserToken();
//            this.nickName = userInfo.getNickName();
//            this.relationship = userInfo.getRelationship();
//            this.status = userInfo.getStatus();
//        }
//    }

    @Getter
    @Setter
    @ToString
    public static class LoginRequest {
        @NotEmpty(message = "accessToken 는 필수값입니다")
        private String accessToken;


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

    @Getter
    @Setter
    @ToString
    public static class ReissueRequest {
        @NotEmpty(message = "refreshToken 는 필수값입니다")
        private String refreshToken;

    }

    @Getter
    @Builder
    @ToString
    public static class ReissueResponse {

        private final String jwtToken;

        private final String refreshToken;

    }


    // 조회
    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long id;

        private final String userToken;

        private final String nickName;

        private final String relationship;

        private final User.Status status;

//        public Main(UserInfo userInfo) {
//            this.id = userInfo.getId();
//            this.userToken = userInfo.getUserToken();
//            this.nickName = userInfo.getNickName();
//            this.relationship = userInfo.getRelationship();
//            this.status = userInfo.getStatus();
//        }
    }




}