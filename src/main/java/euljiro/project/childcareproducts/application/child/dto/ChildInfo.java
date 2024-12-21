package euljiro.project.childcareproducts.application.child.dto;

import euljiro.project.childcareproducts.domain.child.Child;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class ChildInfo {

    @Getter
    @ToString
    public static class ChildRegisterResponse {

        private final String childname;
        private final Child.Status status;
        private final LocalDate birthdate;

        public ChildRegisterResponse(Child child) {
            this.childname = child.getChildName();
            this.status = child.getStatus();
            this.birthdate = child.getBirthday();


        }

    }
}
