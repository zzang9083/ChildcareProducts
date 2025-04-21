package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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

    public GroupChildInfo.RegisterChildByGroupResponse registerChildByGroup(ChildCommand.RegisterChildByGroupRequest childCommand, Group group) {
        var initChild = childCommand.toEntity(group);
        Child child = childStore.store(initChild);
        return new GroupChildInfo.RegisterChildByGroupResponse(child);
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
