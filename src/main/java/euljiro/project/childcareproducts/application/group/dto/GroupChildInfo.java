package euljiro.project.childcareproducts.application.group.dto;

import euljiro.project.childcareproducts.domain.child.Child;
import lombok.Getter;
import lombok.ToString;

public class GroupChildInfo {

    @Getter
    @ToString
    public static class RegisterChildByGroupResponse {
        private long childId;
        private String childToken;


        public RegisterChildByGroupResponse(Child child) {
            this.childId = child.getId();
            this.childToken = child.getChildToken();
        }
    }
}
