package euljiro.project.childcareproducts.application.group;


import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.domain.child.ChildService;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GroupChildApplicationService {

    private final GroupService groupService;

    private final ChildService childService;

    public String registerChildByGroup(ChildCommand.RegisterChildByGroupRequest command) {

        // 그룹 READ
        Group group = groupService.getGroupBy(command.getGroupToken());

        // 아이 SAVE
        GroupChildInfo.RegisterChildToGroupResponse response
                = childService.registerChildToGroup(command, group);

        return response.getChildToken();
    }

    public GroupChildInfo.GetChildrenResponse getChildren(String groupToken) {

        // 그룹 READ
        Group group = groupService.getGroupBy(groupToken);

        // 아이리스트 READ
        return childService.getChildrenBy(group.getId());
    }






//    public void disableCard(String cardToken) {
//        cardService.disableCard(cardToken);
//    }

}
