package euljiro.project.childcareproducts.api.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;


public class UserDto {

    @Getter
    @Builder
    @ToString
    public static class UserInfoRegisterRequest {

        @NotEmpty(message = "userKey는 필수값입니다")
        private final String userKey;

        @NotEmpty(message = "닉네임은 필수값입니다")
        private final String nickname;

        @NotNull(message = "성별은 필수값입니다")
        @ValidEnum(enumClass = User.Gender.class)
        private final User.Gender gender;

        public UserCommand.RegisterUserInfoRequest toUserCommand(String userKey) {
            return UserCommand.RegisterUserInfoRequest.builder()
                    .userKey(userKey)
                    .nickname(nickname)
                    .gender(gender)
                    .build();
        }

    }

    @Getter
    @Builder
    @ToString
    public static class UserInfoRegisterResponse {

        private final String userKey;

        private final String nickname;

        private final User.Gender gender;


    }

    @Getter
    @Builder
    @ToString
    public static class ChildRegisterRequest {

        @NotEmpty(message = "아이이름은 필수값입니다")
        private String childname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthdate;

        @NotNull(message = "아이상태는 필수값입니다")
        @ValidEnum(enumClass = Child.BirthStatus.class)
        private Child.BirthStatus birthstatus;

        public ChildCommand.RegisterChildByUserRequest toChildCommand(String userKey) {
            return ChildCommand.RegisterChildByUserRequest.builder()
                    .registeredUserKey(userKey)
                    .childName(childname)
                    .birthdate(birthdate)
                    .birthStatus(birthstatus)
                    .build();
        }

    }

    @Getter
    @Builder
    @ToString
    public static class ChildRegisterResponse {

        private String childname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDate;

        private Child.BirthStatus birthStatus;


    }


}
