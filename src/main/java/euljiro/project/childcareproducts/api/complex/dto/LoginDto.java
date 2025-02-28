package euljiro.project.childcareproducts.api.complex.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LoginDto {

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

        private final User.Status status;

        private final String token;

        private final String refreshToken;

        private final String groupToken;

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

        private final String userKey;

        private final User.Status status;

        private final String token;

        private final String refreshToken;

        private final String groupToken;

    }

    @Getter
    @Builder
    @ToString
    public static class DashBoardResponse {

        private String childToken;

        private String childName;

        private LocalDate birthDate;

        private Child.BirthStatus birthStatus;

        private List<InquiryHistory> histories;

    }

    @Getter
    @Builder
    @ToString
    public static class InquiryHistory {

        private String productToken;

        private String productName;

        private String itemToken;

        private String itemName;

        private LocalDateTime creationTime;
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
