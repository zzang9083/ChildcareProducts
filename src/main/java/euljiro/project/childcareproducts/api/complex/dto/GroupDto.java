package euljiro.project.childcareproducts.api.complex.dto;

import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.application.complex.dto.GroupCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class GroupDto {

    @Getter
    @Setter
    @ToString
    public static class MatchGroupRequest {
        @NotEmpty(message = "userKey 는 필수값입니다")
        private String userKey;

        @NotEmpty(message = "shareCode 는 필수값입니다")
        private String shareCode;

        public GroupCommand.MatchGroupRequest toCommand() {
            return GroupCommand.MatchGroupRequest.builder()
                    .userKey(userKey)
                    .shareCode(shareCode)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class MatchGroupResponse {
        private String groupToken;

        private List<String> userList;

        private  List<String> childList;
    }
}
