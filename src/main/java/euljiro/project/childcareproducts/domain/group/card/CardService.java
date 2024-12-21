package euljiro.project.childcareproducts.domain.group.card;

import euljiro.project.childcareproducts.application.group.dto.GroupCardCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.domain.group.Group;

public interface CardService {
    GroupCardInfo.RegisterCardResponse registerCard(Group group, GroupCardCommand.RegisterCardRequest command);

    void disableCard(String cardToken);



}
