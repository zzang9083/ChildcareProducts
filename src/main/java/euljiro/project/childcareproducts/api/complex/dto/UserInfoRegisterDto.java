package euljiro.project.childcareproducts.api.complex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserInfoRegisterDto {

    private String nickname;

    private User.Gender gender;

    private String childname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private Child.Status status;

    public UserCommand.RegisterUserInfoRequest toUserCommand(String userKey) {
        return UserCommand.RegisterUserInfoRequest.builder()
                .userKey(userKey)
                .nickname(nickname)
                .gender(gender)
                .build();
    }


    public ChildCommand toChildCommand(String userKey) {
        return ChildCommand.builder()
                .registeredUserKey(userKey)
                .childName(childname)
                .birthdate(birthdate)
                .status(status)
                .build();
    }

    @Getter
    @Builder
    @ToString
    public static class UserInfoRegisterResponse {

        private final String userKey;

        private final String nickname;

        private final String childname;

        private final String relationship;

        private final LocalDate birthdate;

    }

}
