package euljiro.project.childcareproducts.application.child.dto;

import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class ChildCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterChildByUserRequest {
        private String childName;
        private LocalDate birthdate;

        private Child.BirthStatus birthStatus;

        private String registeredUserKey;


        public Child toEntity() {
            return Child.builder()
                    .childName(childName)
                    .birthdate(birthdate)
                    .birthStatus(birthStatus)
                    .userKey(registeredUserKey)
                    .build();

        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterChildByGroupRequest {
        private String registeredUserKey;

        private String groupToken;

        private String childName;
        private LocalDate birthdate;

        private Child.BirthStatus birthStatus;


        public Child toEntity(Group group) {
            Child child = Child.builder()
                    .childName(childName)
                    .birthdate(birthdate)
                    .birthStatus(birthStatus)
                    .userKey(registeredUserKey)
                    .build();

            group.addChild(child);

            return child;
        }
    }



//    @Getter
//    @Builder
//    @ToString
//    public static class RegisterChildInfoRequest {
//
//        private String registeredUserKey;
//
//        private String childName;
//
//        private LocalDate birthdate;
//
//        private Child.Status status;
//    }




}
