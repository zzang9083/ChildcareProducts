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
        private final Child.BirthStatus birthStatus;
        private final LocalDate birthDate;

        public ChildRegisterResponse(Child child) {
            this.childname = child.getChildName();
            this.birthStatus = child.getBirthStatus();
            this.birthDate = child.getBirthDate();

        }
    }
}
