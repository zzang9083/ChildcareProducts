package euljiro.project.childcareproducts.api.complex.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
import euljiro.project.childcareproducts.application.complex.dto.GroupMatchCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class GroupMatchDto {

    @Getter
    @Setter
    @ToString
    public static class MatchGroupRequest {
        @NotEmpty(message = "userKey는 필수값입니다")
        private String userKey;

        @NotEmpty(message = "공유코드는 필수값입니다")
        private String shareCode;

        public GroupMatchCommand.MatchGroupRequest toCommand() {
            return GroupMatchCommand.MatchGroupRequest.builder()
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

        public MatchGroupResponse(GroupMatchInfo.MatchGroupResponse response) {
            groupToken = response.getGroupToken();;
            userList = response.getUserList();
            childList = response.getChildList();
        }


    }
}
