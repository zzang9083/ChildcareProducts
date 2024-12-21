package euljiro.project.childcareproducts.api.complex.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
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
        @NotEmpty(message = "userKey는 필수값입니다")
        private String userKey;

        @NotEmpty(message = "공유코드는 필수값입니다")
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

        private List<String> userList = Lists.newArrayList();

        private  List<String> childList = Lists.newArrayList();

        // 기본 생성자
        public MatchGroupResponse() {}

        public MatchGroupResponse(GroupInfo.MatchGroupResponse response) {
            groupToken = response.getGroupToken();;
            userList = response.getUserList();
            childList = response.getChildList();
        }


    }
}
