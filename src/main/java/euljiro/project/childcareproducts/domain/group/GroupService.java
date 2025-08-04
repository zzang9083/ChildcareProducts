package euljiro.project.childcareproducts.domain.group;

import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.domain.group.card.Card;

public interface GroupService {

    public Group getGroupBy(long groupId);


    public Group getGroupBy(String groupToken);


    GroupMatchInfo.MatchGroupResponse matchGroup(String ownerUserKey, String inputUserKey);

    GroupCardInfo.GetCardsResponse getCardsByGroupToken(String groupToken, Card.Status status);

    void updateStatus(Group group, Group.Status status);

    void changeSelectedChild(String groupToken, long childId);

}
