package euljiro.project.childcareproducts.application.complex.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
public class GroupInfo {

    @Getter
    @ToString
    public static class MatchGroupResponse {

        private final String groupToken;
        private List<String> userList = Lists.newArrayList();

        private  List<String> childList = Lists.newArrayList();


        public MatchGroupResponse(Group group) {
            this.groupToken = group.getGroupToken();

            for(User user : group.getUserList()) {
                userList.add(user.getUserKey());
            }
            for(Child child : group.getChildList()) {
                childList.add(child.getChildToken());
            }
        }

    }



}
