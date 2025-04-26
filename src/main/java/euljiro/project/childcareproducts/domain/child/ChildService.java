package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.domain.group.Group;

public interface ChildService {

    public Child registerChildByUser(ChildCommand.RegisterChildByUserRequest childCommand);

    public GroupChildInfo.RegisterChildToGroupResponse registerChildToGroup(ChildCommand.RegisterChildByGroupRequest childCommand, Group group);


    public Child getChildBy(long childId);


    public GroupChildInfo.GetChildrenResponse getChildrenBy(long groupId);

    public Child getChildBy(String childToken);

    void deleteChild(String childToken);
}
