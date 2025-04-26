package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.child.Child;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GroupChildInfo {

    @Getter
    @ToString
    public static class Main {

        private String childToken;

        private String registeredUserKey;

        private String childName;

        private LocalDate birthDate;

        private Child.BirthStatus birthStatus;

        public Main(Child child) {
            this.childToken = child.getChildToken();
            this.registeredUserKey = child.getRegisteredUserKey();
            this.childName = child.getChildName();
            this.birthDate = child.getBirthDate();
            this.birthStatus = child.getBirthStatus();
        }

    }



    @Getter
    @ToString
    public static class RegisterChildToGroupResponse {
        private long childId;
        private String childToken;


        public RegisterChildToGroupResponse(Child child) {
            this.childId = child.getId();
            this.childToken = child.getChildToken();
        }
    }

    @Getter
    @ToString
    public static class GetChildrenResponse {

        private List<Main> childList;


        public GetChildrenResponse(List<Child> childList) {
            this.childList = childList.stream()
                    .map(Main::new) // Child -> Main 변환
                    .collect(Collectors.toList());
        }
    }
}
