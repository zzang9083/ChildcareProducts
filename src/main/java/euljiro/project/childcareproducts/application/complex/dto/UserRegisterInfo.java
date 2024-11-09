package euljiro.project.childcareproducts.application.complex.dto;

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

        private final String relationship;

        private final String childname;

        private final Child.Status status;
        private final LocalDate birthdate;

        public UserInfoRegisterResponse(User user, Child child) {
            this.userKey = user.getUserKey();
            this.nickname = user.getNickName();
            if(user.getGender().equals("남성")) {
                this.relationship = "아빠";
            } else {
                this.relationship = "엄마";
            }
            this.childname = child.getChildName();
            this.status = child.getStatus();
            this.birthdate = child.getBirthday();


        }

    }


}
