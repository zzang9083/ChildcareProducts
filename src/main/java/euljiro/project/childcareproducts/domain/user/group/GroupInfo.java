package euljiro.project.childcareproducts.domain.user.group;

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
        private List<User> userList;


        public MatchGroupResponse(Group group) {
            this.groupToken = group.getGroupToken();
            this.userList = group.getUserList();
        }

    }



}
