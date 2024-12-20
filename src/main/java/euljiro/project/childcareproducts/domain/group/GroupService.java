package euljiro.project.childcareproducts.domain.group;

import euljiro.project.childcareproducts.application.complex.dto.GroupInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.user.User;
import java.util.*;

public interface GroupService {

    public Group getGroupByToken(String groupToken);

    GroupInfo.MatchGroupResponse matchGroup(String ownerUserKey, String inputUserKey);

    GroupCardInfo.GetCardsResponse getCardsByGroupToken(String groupToken);
}
