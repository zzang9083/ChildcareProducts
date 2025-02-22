package euljiro.project.childcareproducts.application.child.dto;

import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class ChildCommand {

    private String childName;
    private LocalDate birthdate;

    private Child.BirthStatus birthStatus;

    private String registeredUserKey;


    public Child toEntity() {
        return Child.builder()
                .childName(childName)
                .birthday(birthdate)
                .birthStatus(birthStatus)
                .userKey(registeredUserKey)
                .build();

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
