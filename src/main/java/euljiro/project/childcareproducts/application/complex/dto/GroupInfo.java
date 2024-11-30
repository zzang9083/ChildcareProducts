package euljiro.project.childcareproducts.application.complex.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public class GroupInfo {

    @Getter
    @ToString
    public static class BasicInfo {

        private Long id;

        private String groupToken;

        private Group.Status status;
    }

    @Getter
    @Setter
    @ToString
    public static class MatchGroupResponse {

        private long groupId;
        private String groupToken;
        private List<String> userList = Lists.newArrayList();

        private  List<String> childList = Lists.newArrayList();


        public MatchGroupResponse(Group group) {

            log.info("***** MatchGroupResponse.MatchGroupResponse start *****");

            this.groupId = group.getId();

            this.groupToken = group.getGroupToken();

            for(User user : group.getUserList()) {
                userList.add(user.getUserKey());
            }
            for(Child child : group.getChildList()) {
                childList.add(child.getChildToken());
            }

            log.info("***** MatchGroupResponse.MatchGroupResponse end *****");

        }

    }



}
