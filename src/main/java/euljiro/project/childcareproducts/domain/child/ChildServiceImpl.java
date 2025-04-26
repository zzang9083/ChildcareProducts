package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildStore childStore;

    private final ChildReader childReader;


    public Child registerChildByUser(ChildCommand.RegisterChildByUserRequest childCommand) {
        var initChild = childCommand.toEntity();
        return childStore.store(initChild);
    }

    public GroupChildInfo.RegisterChildToGroupResponse registerChildToGroup(ChildCommand.RegisterChildByGroupRequest childCommand, Group group) {
        var initChild = childCommand.toEntity(group);
        Child child = childStore.store(initChild);
        return new GroupChildInfo.RegisterChildToGroupResponse(child);
    }

    public GroupChildInfo.GetChildrenResponse getChildrenBy(long groupId) {
        List<Child> childList = childReader.getActiveChildrenBy(groupId, Child.Status.ACTIVE);

        return new GroupChildInfo.GetChildrenResponse(childList);
    }

//    @Override
//    public void updateChildInfo(String childToken, ChildCommand.) {
//        Child child = childReader.getChildBy(childToken);
//        child.updateInfo();
//
//        childStore.store(child);
//    }

    @Override
    public void deleteChild(String childToken) {
        Child child = childReader.getChildBy(childToken);
        child.disable();

        childStore.store(child);
    }

    @Override
    public Child getChildBy(long childId) {
        return childReader.getChildBy(childId);
    }

    @Override
    public Child getChildBy(String childToken) {
        return childReader.getChildBy(childToken);
    }
}
