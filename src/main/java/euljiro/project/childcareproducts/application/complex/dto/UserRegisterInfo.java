package euljiro.project.childcareproducts.application.complex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class UserRegisterInfo {

    @Getter
    @ToString
    public static class UserInfoRegisterResponse {

        private final String userKey;

        private final String nickname;

        private final User.Gender gender;

        private final String childname;

        private final Child.Status status;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private final LocalDate birthdate;

        public UserInfoRegisterResponse(User user, Child child) {
            this.userKey = user.getUserKey();
            this.nickname = user.getNickName();
            this.gender = user.getGender();
            this.childname = child.getChildName();
            this.status = child.getStatus();
            this.birthdate = child.getBirthday();


        }

    }


}
