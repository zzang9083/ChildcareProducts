package euljiro.project.childcareproducts.application.group;


import euljiro.project.childcareproducts.application.child.dto.ChildCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import euljiro.project.childcareproducts.domain.child.Child;
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
        Group group = groupService.getGroupByToken(command.getGroupToken());

        // 카드 SAVE
        GroupChildInfo.RegisterChildByGroupResponse response
                = childService.registerChildByGroup(command, group);

        return response.getChildToken();
    }

    public void changeSelectedChild(String groupToken, String childToken) {

        Child child = childService.getChildBy(childToken);

        // 그룹 READ
        groupService.changeSelectedChild(groupToken, child.getId());

    }

    public GroupCardInfo.GetCardsResponse getCards(String groupToken) {
        return groupService.getCardsByGroupToken(groupToken);


    }

//    public void disableCard(String cardToken) {
//        cardService.disableCard(cardToken);
//    }

}
