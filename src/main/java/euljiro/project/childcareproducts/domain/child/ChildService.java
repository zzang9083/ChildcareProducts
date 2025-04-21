package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.domain.group.Group;

public interface ChildService {

    public Child registerChildByUser(ChildCommand.RegisterChildByUserRequest childCommand);

    public GroupChildInfo.RegisterChildByGroupResponse registerChildByGroup(ChildCommand.RegisterChildByGroupRequest childCommand, Group group);


    public Child getChildBy(long childId);

    public Child getChildBy(String childToken);
}
