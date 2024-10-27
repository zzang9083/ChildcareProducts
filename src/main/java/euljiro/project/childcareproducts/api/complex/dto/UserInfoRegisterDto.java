package euljiro.project.childcareproducts.api.complex.dto;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.user.dto.UserCommand;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class UserInfoRegisterDto {

    private String userKey;

    private String nickname;

    private String relationship;

    private String childname;
    private LocalDate birthdate;
    private Child.Status status;

    public UserCommand.RegisterUserInfoRequest toUserCommand() {
        return UserCommand.RegisterUserInfoRequest.builder()
                .userKey(userKey)
                .nickname(nickname)
                .gender(convertToGender(relationship))
                .build();
    }

    private User.Gender convertToGender(String relationship) {
        if("1".equals(relationship)) {
            return User.Gender.MALE;
        }
        else{
            return User.Gender.FEMALE;
        }
    }

    public ChildCommand toChildCommand() {
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
