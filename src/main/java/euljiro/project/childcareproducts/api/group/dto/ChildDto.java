package euljiro.project.childcareproducts.api.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.common.exception.ValidEnum;
import euljiro.project.childcareproducts.domain.child.Child;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class ChildDto {


    @Getter
    @Builder
    @ToString
    public static class RegisterChildRequest {

        @NotEmpty(message = "userKey는 필수값입니다")
        private String userKey;

        @NotEmpty(message = "아이이름은 필수값입니다")
        private String childname;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthdate;

        @NotNull(message = "아이상태는 필수값입니다")
        @ValidEnum(enumClass = Child.BirthStatus.class)
        private Child.BirthStatus birthstatus;

        public ChildCommand.RegisterChildByGroupRequest toChildCommand(String groupToken) {
            return ChildCommand.RegisterChildByGroupRequest.builder()
                    .registeredUserKey(userKey)
                    .groupToken(groupToken)
                    .childName(childname)
                    .birthdate(birthdate)
                    .birthStatus(birthstatus)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterChildResponse {

        private String childToken;

        public RegisterChildResponse(String childToken) {
            this.childToken = childToken;
        }
    }
}
