package euljiro.project.childcareproducts.application.group;


import euljiro.project.childcareproducts.application.group.dto.GroupCardCommand;
import euljiro.project.childcareproducts.application.group.dto.GroupCardInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.group.card.CardService;
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GroupCardApplicationService {

    private final UserService userService;

    private final GroupService groupService;

    private final CardService cardService;



    public String registerCard(GroupCardCommand.RegisterCardRequest command) {

        // TOKEN TO ID
        User user = userService.getUser(command.getUserKey());
        command.setUserId(user.getId());

        // 그룹 READ
        Group group = groupService.getGroupBy(command.getGroupToken());

        // 카드 SAVE
        GroupCardInfo.RegisterCardResponse response
                = cardService.registerCard(group, command);
        //tokenUtil.storeIdByToken(response.getCardToken(), response.getCardId());

        return response.getCardToken();
    }

    public GroupCardInfo.GetCardsResponse getCards(String groupToken, Card.Status status) {
        return groupService.getCardsByGroupToken(groupToken, status);


    }

    public void disableCard(String cardToken) {
        cardService.disableCard(cardToken);
    }
}
