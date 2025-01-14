package euljiro.project.childcareproducts.domain.group;

import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;

public interface GroupService {

    public Group getGroupByToken(String groupToken);

    //public Group getDashBoardInfo(String groupToken);

    GroupMatchInfo.MatchGroupResponse matchGroup(String ownerUserKey, String inputUserKey);

    GroupCardInfo.GetCardsResponse getCardsByGroupToken(String groupToken);
}
